package vizsgaremek.coordinate;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateDto {

    private Long id;
    private String name;
    private double latitude;
    private double longitude;
}
