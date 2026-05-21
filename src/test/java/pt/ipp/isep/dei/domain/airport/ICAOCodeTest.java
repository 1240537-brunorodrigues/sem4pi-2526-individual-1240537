package pt.ipp.isep.dei.domain.airport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ICAOCodeTest {

    @Test
    void shouldCreateValidICAOCode() {
        ICAOCode code = new ICAOCode("LPPR");

        assertEquals("LPPR", code.value());
    }

    @Test
    void shouldNormalizeICAOCode() {
        ICAOCode code = new ICAOCode("lppr");

        assertEquals("LPPR", code.value());
    }

    @Test
    void shouldTrimICAOCode() {
        ICAOCode code = new ICAOCode("  LPPR  ");

        assertEquals("LPPR", code.value());
    }

    @Test
    void shouldRejectNullICAOCode() {
        assertThrows(IllegalArgumentException.class, () -> new ICAOCode(null));
    }

    @Test
    void shouldRejectBlankICAOCode() {
        assertThrows(IllegalArgumentException.class, () -> new ICAOCode("   "));
    }

    @Test
    void shouldRejectICAOCodeWithLessThanFourLetters() {
        assertThrows(IllegalArgumentException.class, () -> new ICAOCode("LPP"));
    }

    @Test
    void shouldRejectICAOCodeWithMoreThanFourLetters() {
        assertThrows(IllegalArgumentException.class, () -> new ICAOCode("LPPRR"));
    }

    @Test
    void shouldRejectICAOCodeWithNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new ICAOCode("LPP1"));
    }

    @Test
    void equalICAOCodesShouldBeEqual() {
        ICAOCode code1 = new ICAOCode("LPPR");
        ICAOCode code2 = new ICAOCode("lppr");

        assertEquals(code1, code2);
        assertEquals(code1.hashCode(), code2.hashCode());
    }
}