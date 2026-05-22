package pt.ipp.isep.dei.domain.aircraft.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTypeTest {

    @Test
    void shouldCreateValidEngineType() {
        EngineType type = new EngineType("TURBOFAN");

        assertEquals("TURBOFAN", type.name());
    }

    @Test
    void shouldNormalizeEngineType() {
        EngineType type = new EngineType("turbo fan");

        assertEquals("TURBO_FAN", type.name());
    }

    @Test
    void shouldTrimEngineType() {
        EngineType type = new EngineType("  turbofan  ");

        assertEquals("TURBOFAN", type.name());
    }

    @Test
    void shouldRejectNullEngineType() {
        assertThrows(IllegalArgumentException.class,
                () -> new EngineType(null));
    }

    @Test
    void shouldRejectBlankEngineType() {
        assertThrows(IllegalArgumentException.class,
                () -> new EngineType("   "));
    }

    @Test
    void equalEngineTypesShouldBeEqual() {
        EngineType type1 = new EngineType("turbo fan");
        EngineType type2 = new EngineType("TURBO_FAN");

        assertEquals(type1, type2);
        assertEquals(type1.hashCode(), type2.hashCode());
    }
}