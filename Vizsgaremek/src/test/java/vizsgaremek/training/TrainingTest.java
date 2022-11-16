package vizsgaremek.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vizsgaremek.coordinate.Coordinate;
import vizsgaremek.trackpoint.TrackPoint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTest {

    Training training;

    @BeforeEach
    void init(){
        Coordinate haleszvC = new Coordinate("haleszVegC", 47.195157, 18.439192);
        Coordinate lehelC = new Coordinate("lehelC", 47.197683, 18.443774);
        TrackPoint haleszV = new TrackPoint("Halesz vége", haleszvC, 122.5);
        TrackPoint lehel = new TrackPoint("Lehel utca", lehelC, 126.6);
        training = new Training("futás", "Gergő kocogása", List.of(haleszV, lehel));
    }

    @Test
    void testGetTrainingAllDistance(){
        //System.out.println(training.getTrainingAllDistance());
        assertEquals(445.9, 1000 * training.getTrainingAllDistance(), 0.5);
    }

    @Test
    void testGetTrainingSumElevation(){
        assertEquals(4.1, training.getTrainingSumElevation(), 0.05);
    }

}