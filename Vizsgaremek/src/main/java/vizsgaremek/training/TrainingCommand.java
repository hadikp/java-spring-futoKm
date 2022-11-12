package vizsgaremek.training;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vizsgaremek.trackpoint.TrackPoint;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCommand {

    @NotBlank(message = "Please provide a name.")
    @Schema(description = "name of the training", example = "futás")
    private String name;

    @NotBlank(message = "Please provide a description.")
    @Schema(description = "description of the training", example = "Kora reggeli futás")
    private String description;

    //@NotBlank(message = "Please provide a date.")
    @Schema(example = "2022-05-11")
    private LocalDate date;

    private List<TrackPoint> trackpoints = new ArrayList<>();

    public TrainingCommand(String name, String description, LocalDate date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }
}
