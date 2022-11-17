package vizsgaremek.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vizsgaremek.coordinate.Coordinate;
import vizsgaremek.trackpoint.TrackPoint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTest {

    Training trainingHaleszLehel;
    Training trainingTovarosBalaton;
    Training trainingTovarosHorog;

    @BeforeEach
    void init(){
        Coordinate haleszvC = new Coordinate("haleszVegC", 47.195157, 18.439192);
        Coordinate lehelC = new Coordinate("lehelC", 47.197683, 18.443774);
        Coordinate tovarosC  = new Coordinate(47.182193, 18.410582);
        Coordinate balatoniKorfC  = new Coordinate(47.181378, 18.406818);
        Coordinate tatraUtC  = new Coordinate(47.183988, 18.405065);
        Coordinate horogUtC  = new Coordinate(47.184073, 18.402325);

        TrackPoint haleszV = new TrackPoint("Halesz vége", haleszvC, 122.5);
        TrackPoint lehel = new TrackPoint("Lehel utca", lehelC, 126.6);
        TrackPoint tovaros = new TrackPoint("Tóváros előtt", tovarosC, 1);
        TrackPoint balatoniKorf = new TrackPoint("Balatoni út körforgó", balatoniKorfC, 1);
        TrackPoint tatraUt = new TrackPoint("Tátra út vége", tatraUtC, 1);
        TrackPoint horogUt = new TrackPoint("Horog út eleje", horogUtC, 1);

        trainingHaleszLehel = new Training("futás", "Gergő kocogása", List.of(haleszV, lehel));
        trainingTovarosBalaton = new Training("futás", "Péter futása", List.of(tovaros, balatoniKorf, tatraUt));
        trainingTovarosHorog = new Training("futás", "Péter hosszabb futása", List.of(tovaros, balatoniKorf, tatraUt, horogUt));
    }

    @Test
    void testGetTrainingAllDistance(){
        assertEquals(445.9, trainingHaleszLehel.getTrainingAllDistance(), 0.5);
        assertEquals(617.6, trainingTovarosBalaton.getTrainingAllDistance(), 0.5);
        assertEquals(824.9, trainingTovarosHorog.getTrainingAllDistance(), 0.5);
    }

    @Test
    void testConvertKmAndTwoDigits(){
        double sum = trainingTovarosHorog.getTrainingAllDistance();
        assertEquals(0.82, trainingTovarosHorog.convertKmAndTwoDigits(sum), 0.01);
    }
    @Test
    void testGetTrainingSumElevation(){
        assertEquals(4.1, trainingHaleszLehel.getTrainingSumElevation(), 0.05);
    }
}