package vizsgaremek.trackpoint;

import org.springframework.data.jpa.repository.JpaRepository;
import vizsgaremek.trackpoint.TrackPoint;

public interface TrackPointRepository extends JpaRepository<TrackPoint, Long> {
}
