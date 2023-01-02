package vizsgaremek.trackpoint;

import org.junit.jupiter.api.Test;
import vizsgaremek.coordinate.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class TrackPointTest {

    TrackPoint trackPoint = new TrackPoint(new Coordinate(12.5, 34.89), 123);
    TrackPoint trackPoint2 = new TrackPoint(new Coordinate(12.6, 34.123), 200);

    @Test
    void testCreateTrackPoint(){
        assertEquals(34.89, trackPoint.getCoordinate().getLongitude());
        assertEquals(12.5, trackPoint.getCoordinate().getLatitude());
        assertEquals(123.0, trackPoint.getElevation());
    }

    @Test
    void testGetDistance(){
        assertTrue(trackPoint.getDistanceFrom(trackPoint2) > 83988.01 && trackPoint.getDistanceFrom(trackPoint2) < 83988.11);
    }

    @Test
    void testDistanceFehervar(){
        Coordinate tovarosC  = new Coordinate(47.182193, 18.410582);
        TrackPoint tovaros = new TrackPoint(tovarosC, 1);
        Coordinate lovoldev = new Coordinate(47.1864454,18.424767);
        TrackPoint lovoldevege = new TrackPoint(lovoldev, 2);
        Coordinate lovolde1C = new Coordinate(47.190135, 18.421741);
        TrackPoint lovolde1 = new TrackPoint(lovolde1C, 2);
        Coordinate haleszC = new Coordinate(47.194164, 18.431535);
        TrackPoint halesz = new TrackPoint(haleszC, 1);

        Coordinate kertBalFelC =new Coordinate(47.211827, 18.276724);
        TrackPoint kertBalFel = new TrackPoint(kertBalFelC, 1);
        Coordinate kertBalAlC =new Coordinate(47.210908, 18.276592);
        TrackPoint kertBalAl = new TrackPoint(kertBalAlC, 1);

        assertEquals(1221.07, tovaros.getDistanceFrom(lovolde1), 0.5);
        assertEquals(1171.6, tovaros.getDistanceFrom(lovoldevege), 0.5);
        assertEquals(2068.5, tovaros.getDistanceFrom(halesz), 0.5);


        assertEquals(102.67, kertBalFel.getDistanceFrom(kertBalAl), 0.5); //Bal hosszú

        Coordinate kertJobbFelC =new Coordinate(47.211954, 18.276946);
        TrackPoint kertJobbFel = new TrackPoint(kertJobbFelC, 1);
        Coordinate kertJobbAlC =new Coordinate(47.210910, 18.276790);
        TrackPoint kertJobbAl = new TrackPoint(kertJobbAlC, 1);

        assertEquals(116.7, kertJobbAl.getDistanceFrom(kertJobbFel), 0.5); //Jobb Hosszú
        assertEquals(15, kertBalAl.getDistanceFrom(kertJobbAl), 0.5); //kert alja
        assertEquals(22, kertJobbFel.getDistanceFrom(kertBalFel), 0.5);
    }

}