package vizsgaremek.coordinate;

import lombok.*;

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
