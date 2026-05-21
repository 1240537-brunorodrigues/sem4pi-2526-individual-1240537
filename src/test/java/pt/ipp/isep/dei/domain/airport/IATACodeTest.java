package pt.ipp.isep.dei.domain.airport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IATACodeTest {

    @Test
    void shouldCreateValidIATACode() {
        IATACode code = new IATACode("OPO");

        assertEquals("OPO", code.value());
    }

    @Test
    void shouldNormalizeIATACode() {
        IATACode code = new IATACode("opo");

        assertEquals("OPO", code.value());
    }

    @Test
    void shouldTrimIATACode() {
        IATACode code = new IATACode("  OPO  ");

        assertEquals("OPO", code.value());
    }

    @Test
    void shouldRejectNullIATACode() {
        assertThrows(IllegalArgumentException.class, () -> new IATACode(null));
    }

    @Test
    void shouldRejectBlankIATACode() {
        assertThrows(IllegalArgumentException.class, () -> new IATACode("   "));
    }

    @Test
    void shouldRejectIATACodeWithLessThanThreeLetters() {
        assertThrows(IllegalArgumentException.class, () -> new IATACode("OP"));
    }

    @Test
    void shouldRejectIATACodeWithMoreThanThreeLetters() {
        assertThrows(IllegalArgumentException.class, () -> new IATACode("OPOO"));
    }

    @Test
    void shouldRejectIATACodeWithNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new IATACode("OP1"));
    }

    @Test
    void equalIATACodesShouldBeEqual() {
        IATACode code1 = new IATACode("OPO");
        IATACode code2 = new IATACode("opo");

        assertEquals(code1, code2);
        assertEquals(code1.hashCode(), code2.hashCode());
    }
}