package pt.ipp.isep.dei.domain.aircraft.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineManufacturerTest {

    @Test
    void shouldCreateValidEngineManufacturer() {
        EngineManufacturer manufacturer = new EngineManufacturer("Rolls-Royce", "United Kingdom");

        assertEquals("Rolls-Royce", manufacturer.name());
        assertEquals("United Kingdom", manufacturer.country());
    }

    @Test
    void shouldTrimNameAndCountry() {
        EngineManufacturer manufacturer = new EngineManufacturer("  Rolls-Royce  ", "  United Kingdom  ");

        assertEquals("Rolls-Royce", manufacturer.name());
        assertEquals("United Kingdom", manufacturer.country());
    }

    @Test
    void shouldRejectNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new EngineManufacturer(null, "United Kingdom"));
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new EngineManufacturer("   ", "United Kingdom"));
    }

    @Test
    void shouldRejectNullCountry() {
        assertThrows(IllegalArgumentException.class,
                () -> new EngineManufacturer("Rolls-Royce", null));
    }

    @Test
    void shouldRejectBlankCountry() {
        assertThrows(IllegalArgumentException.class,
                () -> new EngineManufacturer("Rolls-Royce", "   "));
    }

    @Test
    void equalManufacturersShouldBeEqualIgnoringCase() {
        EngineManufacturer manufacturer1 = new EngineManufacturer("Rolls-Royce", "United Kingdom");
        EngineManufacturer manufacturer2 = new EngineManufacturer("rolls-royce", "united kingdom");

        assertEquals(manufacturer1, manufacturer2);
        assertEquals(manufacturer1.hashCode(), manufacturer2.hashCode());
    }
}