package pt.ipp.isep.dei.domain.aircontrol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaCodeTest {

    @Test
    void shouldCreateValidAreaCode() {
        AreaCode code = new AreaCode("LPPC");

        assertEquals("LPPC", code.value());
    }

    @Test
    void shouldNormalizeAreaCodeToUpperCase() {
        AreaCode code = new AreaCode("lppc");

        assertEquals("LPPC", code.value());
    }

    @Test
    void shouldTrimAreaCode() {
        AreaCode code = new AreaCode("  LPPC  ");

        assertEquals("LPPC", code.value());
    }

    @Test
    void shouldRejectNullAreaCode() {
        assertThrows(IllegalArgumentException.class, () -> new AreaCode(null));
    }

    @Test
    void shouldRejectBlankAreaCode() {
        assertThrows(IllegalArgumentException.class, () -> new AreaCode("   "));
    }

    @Test
    void shouldRejectAreaCodeWithNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new AreaCode("LP1"));
    }

    @Test
    void equalAreaCodesShouldBeEqual() {
        AreaCode code1 = new AreaCode("LPPC");
        AreaCode code2 = new AreaCode("lppc");

        assertEquals(code1, code2);
        assertEquals(code1.hashCode(), code2.hashCode());
    }
}