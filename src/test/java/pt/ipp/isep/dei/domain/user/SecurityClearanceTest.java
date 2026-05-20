package pt.ipp.isep.dei.domain.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SecurityClearanceTest {

    @Test
    void shouldCreateSecurityClearance() {
        LocalDate expirationDate = LocalDate.of(2026, 12, 31);

        SecurityClearance clearance = new SecurityClearance(expirationDate);

        assertEquals(expirationDate, clearance.expirationDate());
    }

    @Test
    void shouldRejectNullExpirationDate() {
        assertThrows(IllegalArgumentException.class, () -> new SecurityClearance(null));
    }

    @Test
    void shouldBeValidBeforeExpirationDate() {
        SecurityClearance clearance = new SecurityClearance(LocalDate.of(2026, 12, 31));

        assertTrue(clearance.isValidOn(LocalDate.of(2026, 1, 1)));
    }

    @Test
    void shouldBeValidOnExpirationDate() {
        SecurityClearance clearance = new SecurityClearance(LocalDate.of(2026, 12, 31));

        assertTrue(clearance.isValidOn(LocalDate.of(2026, 12, 31)));
    }

    @Test
    void shouldBeExpiredAfterExpirationDate() {
        SecurityClearance clearance = new SecurityClearance(LocalDate.of(2026, 12, 31));

        assertTrue(clearance.isExpiredOn(LocalDate.of(2027, 1, 1)));
    }

    @Test
    void equalSecurityClearancesShouldBeEqual() {
        SecurityClearance clearance1 = new SecurityClearance(LocalDate.of(2026, 12, 31));
        SecurityClearance clearance2 = new SecurityClearance(LocalDate.of(2026, 12, 31));

        assertEquals(clearance1, clearance2);
        assertEquals(clearance1.hashCode(), clearance2.hashCode());
    }
}