package vizsgaremek.trackpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/track")
@Tag(name = "Operations the trackpoints")
public class TrackPointController {

    private TrackPointService service;

    public TrackPointController(TrackPointService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "get the trackpoints")
    public List<TrackPointDto> listAllTrackPoint(@RequestParam Optional<Double> elevation){
        return service.listAllTrackpoint(elevation);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a trackpoint by id")
    public TrackPointDto findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a trackpoint")
    @ApiResponse(responseCode = "201", description = "trackpoint has been created")
    public TrackPointDto createTrackpoint(@Valid @RequestBody TrackPointCommand command){
        return service.createTrackpoint(command);
    }

    @PutMapping("/name/{id}")
    @Operation(summary = "update a trackpoint")
    public TrackPointDto updateTrackByName(@PathVariable("id") Long id, @Valid @RequestBody UpdateTrackPointByNameCommand command){
        return service.updateTrackPoint(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete a trackpoint")
    @ApiResponse(responseCode = "204", description = "trackpoint has been deleted")
    public void deleteTrackPoint(@PathVariable("id") Long id){
        service.deleteTrack(id);
    }
}
