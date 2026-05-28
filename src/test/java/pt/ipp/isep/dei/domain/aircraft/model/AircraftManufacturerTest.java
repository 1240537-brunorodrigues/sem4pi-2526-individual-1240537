package pt.ipp.isep.dei.domain.aircraft.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftManufacturerTest {

    @Test
    void shouldCreateValidAircraftManufacturer() {
        AircraftManufacturer manufacturer = new AircraftManufacturer("Airbus", "France");

        assertEquals("Airbus", manufacturer.name());
        assertEquals("France", manufacturer.country());
    }

    @Test
    void shouldTrimNameAndCountry() {
        AircraftManufacturer manufacturer = new AircraftManufacturer("  Airbus  ", "  France  ");

        assertEquals("Airbus", manufacturer.name());
        assertEquals("France", manufacturer.country());
    }

    @Test
    void shouldRejectNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new AircraftManufacturer(null, "France"));
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new AircraftManufacturer("   ", "France"));
    }

    @Test
    void shouldRejectNullCountry() {
        assertThrows(IllegalArgumentException.class,
                () -> new AircraftManufacturer("Airbus", null));
    }

    @Test
    void shouldRejectBlankCountry() {
        assertThrows(IllegalArgumentException.class,
                () -> new AircraftManufacturer("Airbus", "   "));
    }

    @Test
    void equalManufacturersShouldBeEqualIgnoringCase() {
        AircraftManufacturer manufacturer1 = new AircraftManufacturer("Airbus", "France");
        AircraftManufacturer manufacturer2 = new AircraftManufacturer("airbus", "france");

        assertEquals(manufacturer1, manufacturer2);
        assertEquals(manufacturer1.hashCode(), manufacturer2.hashCode());
    }
}