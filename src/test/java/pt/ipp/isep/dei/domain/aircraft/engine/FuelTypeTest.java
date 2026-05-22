package pt.ipp.isep.dei.domain.aircraft.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelTypeTest {

    @Test
    void shouldCreateValidFuelType() {
        FuelType fuelType = new FuelType("JET_A1");

        assertEquals("JET_A1", fuelType.name());
    }

    @Test
    void shouldNormalizeFuelType() {
        FuelType fuelType = new FuelType("jet a1");

        assertEquals("JET_A1", fuelType.name());
    }

    @Test
    void shouldTrimFuelType() {
        FuelType fuelType = new FuelType("  jet_a1  ");

        assertEquals("JET_A1", fuelType.name());
    }

    @Test
    void shouldRejectNullFuelType() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuelType(null));
    }

    @Test
    void shouldRejectBlankFuelType() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuelType("   "));
    }

    @Test
    void equalFuelTypesShouldBeEqual() {
        FuelType fuelType1 = new FuelType("jet a1");
        FuelType fuelType2 = new FuelType("JET_A1");

        assertEquals(fuelType1, fuelType2);
        assertEquals(fuelType1.hashCode(), fuelType2.hashCode());
    }
}