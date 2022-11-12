package vizsgaremek.coordinate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from coordinates")
class CoordinateControllerWebClientITTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CoordinateService service;

    CoordinateDto coordinateDto;

    @BeforeEach
    void init(){
        service.createCoordinate(new CoordinateCommand("tovarosC", 47.182193, 18.410582));
        coordinateDto = service.createCoordinate(new CoordinateCommand("lovolde1C", 47.190135, 18.421741));
    }

    @Test
    void testListAllCoordinate() {
        webTestClient.get().uri("/api/coordinate")
                .exchange()
                .expectBodyList(CoordinateDto.class)
                .value(c -> assertThat(c).extracting(CoordinateDto::getName).contains("tovarosC", "lovolde1C"));
    }

    @Test
    void testFindById(){
        webTestClient.get().uri("/api/coordinate/{id}", coordinateDto.getId())
                .exchange()
                .expectBody(CoordinateDto.class)
                .value(c -> assertEquals("lovolde1C", coordinateDto.getName()))
                .value(c -> assertEquals(47.190135, coordinateDto.getLatitude()))
                .value(c -> assertEquals(18.421741, coordinateDto.getLongitude()));
    }

    @Test
    void testCreateCoordinate(){
        webTestClient.post().uri("/api/coordinate/create")
                .bodyValue(new CoordinateCommand("haleszC", 47.194164, 18.431535))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CoordinateDto.class)
                .value(CoordinateDto -> assertThat(CoordinateDto.getName()).isEqualTo("haleszC"))
                .value(CoordinateDto -> assertThat(CoordinateDto.getLatitude()).isEqualTo(47.194164))
                .value(CoordinateDto -> assertThat(CoordinateDto.getLongitude()).isEqualTo(18.431535));
    }

    @Test
    void testUpdateCoordinateLatitude(){
        webTestClient.put().uri("/api/coordinate/lat/{id}", coordinateDto.getId())
                .bodyValue(new UpdateCoordinateLatitude(48.111222))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CoordinateDto.class)
                .value(CoordinateDto -> assertThat(CoordinateDto.getLatitude()).isEqualTo(48.111222));
    }

    @Test
    void testDeleteCoordinate(){
        webTestClient.delete().uri("/api/coordinate/{id}", coordinateDto.getId())
                .exchange().expectStatus().isNoContent();
        webTestClient.get().uri("/api/coordinate")
                .exchange()
                .expectBodyList(CoordinateDto.class)
                .value(c -> assertThat(c).hasSize(1).extracting(CoordinateDto::getName).containsOnly("tovarosC"));
    }

    @Test
    void testCoordinateNotFound(){
        Problem p = webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/coordinate/{id}").build(111))
                .exchange()
                .expectBody(Problem.class).returnResult().getResponseBody();
        assertEquals("Coordinate not found: 111", p.getDetail());
    }

    @Test
    void testCreateCoordinateWithTooSmallLatitude(){
        ConstraintViolationProblem cvp = webTestClient.post()
                .uri("/api/coordinate/create")
                .bodyValue(new CoordinateCommand("haleszC", 39.444555, 18.431535))
                .exchange()
                .expectBody(ConstraintViolationProblem.class).returnResult().getResponseBody();
        assertEquals("The latitude must be greater than 40", cvp.getViolations().get(0).getMessage());
    }

    @Test
    void testCreateCoordinateWithTooHighLongitude(){
        ConstraintViolationProblem cvp = webTestClient.post()
                .uri("/api/coordinate/create")
                .bodyValue(new CoordinateCommand("haleszC", 47.194164, 26.431535))
                .exchange()
                .expectBody(ConstraintViolationProblem.class).returnResult().getResponseBody();
        assertEquals("The longitude must be smaller than 25", cvp.getViolations().get(0).getMessage());
    }



}