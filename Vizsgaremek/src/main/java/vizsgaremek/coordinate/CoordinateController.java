package vizsgaremek.coordinate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.usertype.DynamicParameterizedType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/coordinate")
@Tag(name = "Operations the coordinates")
public class CoordinateController {

    private CoordinateService service;

    public CoordinateController(CoordinateService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "get the coordinates")
    public List<CoordinateDto> listAllCoordinate(){
        return service.listAllCoordinate();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a coordinate by id")
    public CoordinateDto findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a coordinate")
    @ApiResponse(responseCode = "201", description = "coordinates has been created")
    public CoordinateDto createCoordinate(@Valid @RequestBody CoordinateCommand command){
        return service.createCoordinate(command);
    }

    @PutMapping("/lat/{id}")
    @Operation(summary = "update a latitude")
    public CoordinateDto updateCoordinateLatitude(@PathVariable("id") Long id, @RequestBody UpdateCoordinateLatitude command){
        return service.updateCoordinateLatitude(id, command);
    }

    @PutMapping("/long/{id}")
    @Operation(summary = "update a longitude")
    public CoordinateDto updateCoordinateLongitude(@PathVariable("id") Long id, UpdateCoordinateLongitude command){
        return service.updateCoordinateLongitude(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete a coordinate")
    @ApiResponse(responseCode = "204", description = "coordinates has been deleted")
    public void deleteCoordinate(@PathVariable("id") Long id){
        service.deleteCoordinate(id);
    }
}
