package vizsgaremek.trackpoint;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vizsgaremek.training.Training;
import vizsgaremek.coordinate.Coordinate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trackpoints")
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Coordinate coordinate;
    private double elevation; //magasság

    @ManyToMany(mappedBy = "trackpoints")
    private List<Training> trainings = new ArrayList<>();

    public TrackPoint(Coordinate coordinate, double elevation) {
        this.coordinate = coordinate;
        this.elevation = elevation;
    }

    public TrackPoint(String name, Coordinate coordinate, double elevation) {
        this(coordinate, elevation);
        this.name = name;
    }

    public TrackPoint(String name, double elevation) {
        this.name = name;
        this.elevation = elevation;
    }

    public double getDistanceFrom(TrackPoint point) {
        final int REarth = 6371;
        double latDistance = Math.toRadians(point.getCoordinate().getLatitude() - this.coordinate.getLatitude());
        double lonDistance = Math.toRadians(point.getCoordinate().getLongitude() - this.coordinate.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(this.coordinate.getLatitude()))
                * Math.cos(Math.toRadians(point.getCoordinate().getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = REarth * c * 1000;
        double height = this.elevation - point.getElevation();
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }


}
