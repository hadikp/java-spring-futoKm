package vizsgaremek.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;
import vizsgaremek.coordinate.Coordinate;
import vizsgaremek.trackpoint.TrackPoint;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from trainings")
class TrainingControllerWebClientITTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    TrainingService service;

    TrainingDto trainingDto;

    @BeforeEach
    void init(){
        Coordinate haleszvC = new Coordinate("haleszVegC", 47.195157, 18.439192);
        Coordinate lehelC = new Coordinate("lehelC", 47.197683, 18.443774);
        TrackPoint haleszV = new TrackPoint("Halesz vége", haleszvC, 114.5);
        TrackPoint lehel = new TrackPoint("Lehel utca", lehelC, 124.5);
        service.createTraining(new TrainingCommand("futás", "Reggeli kocogás",
                LocalDate.of(2022, 06, 18), List.of(haleszV, lehel)));

        Coordinate lehelGorkijC = new Coordinate("leheGorkij", 47.197722, 18.443841);
        Coordinate gorkij1C = new Coordinate("gorkij1C", 47.201353, 18.436567);
        TrackPoint lehelGorkij = new TrackPoint("Lehel Gorkij út találkozása", lehelGorkijC, 117.5);
        TrackPoint gorkij = new TrackPoint("Gorkij út eleje", gorkij1C, 121.5);
        trainingDto = service.createTraining(new TrainingCommand("bicigli", "Esti bicajozás",
                LocalDate.of(2022, 06, 19), List.of(lehelGorkij, gorkij)));
    }

    @Test
    void testListAllTraining(){
        webTestClient.get().uri("api/training")
                .exchange()
                .expectBodyList(TrainingDto.class)
                .value(t -> assertThat(t).extracting(TrainingDto::getName).contains("futás"))
                .value(t -> assertThat(t).extracting(TrainingDto::getDescription).contains("Reggeli kocogás"));
    }

    @Test
    void testFindById(){
        webTestClient.get().uri("api/training/{id}", trainingDto.getId())
                .exchange()
                .expectBody(TrainingDto.class)
                .value(t -> assertEquals("bicigli", trainingDto.getName()))
                .value(t -> assertEquals("Esti bicajozás", trainingDto.getDescription()));
    }

    @Test
    void testDeleteTraining(){
        webTestClient.delete().uri("api/training/{id}", trainingDto.getId())
                .exchange().expectStatus().isNoContent();
        webTestClient.get().uri("api/training")
                .exchange()
                .expectBodyList(TrainingDto.class)
                .value(t -> assertThat(t).hasSize(1).extracting(TrainingDto::getName).containsOnly("futás"));

    }

    @Test
    void testTrainingNotFound(){
        Problem p = webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/training/{id}").build(111))
                .exchange()
                .expectBody(Problem.class).returnResult().getResponseBody();
        assertEquals("Training not found: 111", p.getDetail());
    }

    @Test
    void testCreateTraining(){
        webTestClient.post().uri("api/training/create")
                .bodyValue(new TrainingCommand("futás", "Új reggeli futás", LocalDate.of(2022, 06, 29)))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TrainingDto.class)
                .value(TrainingDto -> assertThat(TrainingDto.getName()).isEqualTo("futás"))
                .value(TrainingDto -> assertThat(TrainingDto.getDescription()).isEqualTo("Új reggeli futás"))
                .value(TrainingDto -> assertThat(TrainingDto.getDate()).isEqualTo(LocalDate.of(2022, 06, 29)));

    }

}