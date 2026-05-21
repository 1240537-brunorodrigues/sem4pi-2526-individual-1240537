package pt.ipp.isep.dei.domain.geo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographicBoundaryTest {

    private List<Coordinate> validCoordinates() {
        return List.of(
                new Coordinate(41.0, -8.0),
                new Coordinate(42.0, -8.0),
                new Coordinate(42.0, -7.0)
        );
    }

    @Test
    void shouldCreateValidGeographicBoundary() {
        GeographicBoundary boundary = new GeographicBoundary(validCoordinates());

        assertEquals(3, boundary.size());
    }

    @Test
    void shouldRejectNullCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> new GeographicBoundary(null));
    }

    @Test
    void shouldRejectLessThanThreeCoordinates() {
        List<Coordinate> coordinates = List.of(
                new Coordinate(41.0, -8.0),
                new Coordinate(42.0, -8.0)
        );

        assertThrows(IllegalArgumentException.class, () -> new GeographicBoundary(coordinates));
    }

    @Test
    void shouldRejectNullCoordinateInsideList() {
        List<Coordinate> coordinates = java.util.Arrays.asList(
                new Coordinate(41.0, -8.0),
                null,
                new Coordinate(42.0, -7.0)
        );

        assertThrows(IllegalArgumentException.class, () -> new GeographicBoundary(coordinates));
    }

    @Test
    void coordinatesCollectionShouldBeImmutable() {
        GeographicBoundary boundary = new GeographicBoundary(validCoordinates());

        assertThrows(UnsupportedOperationException.class,
                () -> boundary.coordinates().add(new Coordinate(43.0, -9.0)));
    }

    @Test
    void equalBoundariesShouldBeEqual() {
        GeographicBoundary boundary1 = new GeographicBoundary(validCoordinates());
        GeographicBoundary boundary2 = new GeographicBoundary(validCoordinates());

        assertEquals(boundary1, boundary2);
        assertEquals(boundary1.hashCode(), boundary2.hashCode());
    }
}