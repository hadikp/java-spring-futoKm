package vizsgaremek.trackpoint;

import lombok.*;
import vizsgaremek.coordinate.CoordinateDto;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackPointDto {

    private Long id;
    private String name;
    private double elevation;
    private CoordinateDto coordinate;
}
