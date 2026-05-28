package pt.ipp.isep.dei.domain.aircraft.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTypeTest {

    @Test
    void shouldCreateValidAircraftType() {
        AircraftType type = new AircraftType("WIDE_BODY");

        assertEquals("WIDE_BODY", type.name());
    }

    @Test
    void shouldNormalizeAircraftType() {
        AircraftType type = new AircraftType("wide body");

        assertEquals("WIDE_BODY", type.name());
    }

    @Test
    void shouldTrimAircraftType() {
        AircraftType type = new AircraftType("  wide_body  ");

        assertEquals("WIDE_BODY", type.name());
    }

    @Test
    void shouldRejectNullAircraftType() {
        assertThrows(IllegalArgumentException.class,
                () -> new AircraftType(null));
    }

    @Test
    void shouldRejectBlankAircraftType() {
        assertThrows(IllegalArgumentException.class,
                () -> new AircraftType("   "));
    }

    @Test
    void equalAircraftTypesShouldBeEqual() {
        AircraftType type1 = new AircraftType("wide body");
        AircraftType type2 = new AircraftType("WIDE_BODY");

        assertEquals(type1, type2);
        assertEquals(type1.hashCode(), type2.hashCode());
    }
}