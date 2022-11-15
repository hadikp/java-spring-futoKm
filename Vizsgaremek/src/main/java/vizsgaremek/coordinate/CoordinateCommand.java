package vizsgaremek.coordinate;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateCommand {

    @NotBlank(message = "Please provide a name.")
    @Schema(description = "name of the coordinate", example = "berényi1C")
    private String name;

    @Min(value = 40, message = "The latitude must be greater than 40")
    @Max(value = 50, message = "The latitude must be smaller than 50")
    @Schema(example = "47.198211")
    private double latitude;

    @Min(value = 15, message = "The longitude must be greater than 15")
    @Max(value = 25, message = "The longitude must be smaller than 25")
    @Schema(example = "18.415458")
    private double longitude;
}
