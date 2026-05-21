package pt.ipp.isep.dei.domain.aircontrol;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirControlAreaTest {

    private GeographicBoundary validBoundary() {
        return new GeographicBoundary(List.of(
                new Coordinate(41.0, -8.0),
                new Coordinate(42.0, -8.0),
                new Coordinate(42.0, -7.0)
        ));
    }

    @Test
    void shouldCreateValidAirControlArea() {
        AirControlArea area = new AirControlArea(
                new AreaCode("LPPC"),
                "Lisbon FIR",
                validBoundary()
        );

        assertEquals(new AreaCode("LPPC"), area.code());
        assertEquals("Lisbon FIR", area.name());
        assertEquals(validBoundary(), area.geographicBoundary());
    }

    @Test
    void shouldRejectNullCode() {
        assertThrows(IllegalArgumentException.class, () -> new AirControlArea(
                null,
                "Lisbon FIR",
                validBoundary()
        ));
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new AirControlArea(
                new AreaCode("LPPC"),
                "   ",
                validBoundary()
        ));
    }

    @Test
    void shouldRejectNullBoundary() {
        assertThrows(IllegalArgumentException.class, () -> new AirControlArea(
                new AreaCode("LPPC"),
                "Lisbon FIR",
                null
        ));
    }

    @Test
    void areasWithSameCodeShouldBeEqual() {
        AirControlArea area1 = new AirControlArea(
                new AreaCode("LPPC"),
                "Lisbon FIR",
                validBoundary()
        );

        AirControlArea area2 = new AirControlArea(
                new AreaCode("lppc"),
                "Different Name",
                validBoundary()
        );

        assertEquals(area1, area2);
        assertEquals(area1.hashCode(), area2.hashCode());
    }
}