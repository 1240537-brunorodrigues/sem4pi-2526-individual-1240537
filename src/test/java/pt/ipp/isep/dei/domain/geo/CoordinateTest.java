package pt.ipp.isep.dei.domain.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void shouldCreateValidCoordinate() {
        Coordinate coordinate = new Coordinate(41.1579, -8.6291);

        assertEquals(41.1579, coordinate.latitude());
        assertEquals(-8.6291, coordinate.longitude());
    }

    @Test
    void shouldAcceptMinimumLatitude() {
        Coordinate coordinate = new Coordinate(-90, 0);

        assertEquals(-90, coordinate.latitude());
    }

    @Test
    void shouldAcceptMaximumLatitude() {
        Coordinate coordinate = new Coordinate(90, 0);

        assertEquals(90, coordinate.latitude());
    }

    @Test
    void shouldAcceptMinimumLongitude() {
        Coordinate coordinate = new Coordinate(0, -180);

        assertEquals(-180, coordinate.longitude());
    }

    @Test
    void shouldAcceptMaximumLongitude() {
        Coordinate coordinate = new Coordinate(0, 180);

        assertEquals(180, coordinate.longitude());
    }

    @Test
    void shouldRejectLatitudeBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinate(-90.1, 0));
    }

    @Test
    void shouldRejectLatitudeAboveMaximum() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinate(90.1, 0));
    }

    @Test
    void shouldRejectLongitudeBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinate(0, -180.1));
    }

    @Test
    void shouldRejectLongitudeAboveMaximum() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinate(0, 180.1));
    }

    @Test
    void equalCoordinatesShouldBeEqual() {
        Coordinate coordinate1 = new Coordinate(41.1579, -8.6291);
        Coordinate coordinate2 = new Coordinate(41.1579, -8.6291);

        assertEquals(coordinate1, coordinate2);
        assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
    }
}