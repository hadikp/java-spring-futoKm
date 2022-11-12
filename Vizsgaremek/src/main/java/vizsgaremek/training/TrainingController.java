package vizsgaremek.training;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vizsgaremek.trackpoint.TrackPointCommand;
import vizsgaremek.trackpoint.TrackPointDto;
import vizsgaremek.trackpoint.UpdateTrackPointByNameCommand;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/training")
@Tag(name = "Operations the trainings")
public class TrainingController {

    private TrainingService service;

    public TrainingController(TrainingService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "get the trainings")
    public List<TrainingDto> listAllTrackPoint(@RequestParam Optional<LocalDate> date){
        return service.listAllTraining(date);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a training by id")
    public TrainingDto findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @GetMapping("/sum/{id}")
    @Operation(summary = "get the training's length in metres")
    public double getSumKm(@PathVariable("id") Long id){
        return service.getSumKm(id);
    }

    @GetMapping("/elevation/{id}")
    @Operation(summary = "get the training's total elevation in metres")
    public double getSumElevation(@PathVariable("id") Long id){
        return service.getSumElevation(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a training")
    @ApiResponse(responseCode = "201", description = "training has been created")
    public TrainingDto createTraining(@Valid @RequestBody TrainingCommand command){
        return service.createTraining(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete a training")
    @ApiResponse(responseCode = "204", description = "training has been deleted")
    public void deleteTraining(@PathVariable("id") Long id){
        service.deleteTraining(id);
    }
}
