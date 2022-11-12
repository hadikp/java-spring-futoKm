package vizsgaremek.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vizsgaremek.trackpoint.TrackPoint;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(name = "training_date")
    private LocalDate date;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "training_trackpoint", joinColumns = @JoinColumn(name = "training_id"),
    inverseJoinColumns = @JoinColumn(name = "trackpoint_id"))
    private List<TrackPoint> trackpoints = new ArrayList<>();

    public Training(String name, String description, LocalDate date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Training(String name, String description, List<TrackPoint> trackpoints) {
        this.name = name;
        this.description = description;
        this.trackpoints = trackpoints;
    }

    public void addTrackpoint(TrackPoint trackPoint) {
        trackpoints.add(trackPoint);
        trackPoint.getTrainings().add(this);
    }

    public double getTrainingAllDistance() {
        double sum = 0;
        for (int i = 0; i < trackpoints.size() - 1; i++) {
            double distance = trackpoints.get(i).getDistanceFrom(trackpoints.get(i+1));
            sum += distance;
            System.out.println(sum);
        }
        return sum;
    }

    public double getTrainingSumElevation(){
        double sum = 0;
        for (int i = 1; i < trackpoints.size(); i++){
            double elevationDifference = trackpoints.get(i).getElevation() - trackpoints.get(i-1).getElevation();
            if(elevationDifference > 0) {
                sum += elevationDifference;
            }
        }
        return sum;
    }
}
