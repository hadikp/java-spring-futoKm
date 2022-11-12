package vizsgaremek.coordinate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vizsgaremek.coordinate.Coordinate;

import java.util.List;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

    //@Query("Select c from Coordinate c join fetch c.trackPoint")
    //List<Coordinate> findAllCoordinate();

}
