package vizsgaremek.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vizsgaremek.trackpoint.TrackPoint;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private double km;
}
