package pt.ipp.isep.dei.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        Email email = new Email("john.doe@example.com");

        assertEquals("john.doe@example.com", email.value());
    }

    @Test
    void shouldNormalizeEmailToLowerCase() {
        Email email = new Email("John.Doe@Example.COM");

        assertEquals("john.doe@example.com", email.value());
    }

    @Test
    void shouldTrimEmail() {
        Email email = new Email("  john.doe@example.com  ");

        assertEquals("john.doe@example.com", email.value());
    }

    @Test
    void shouldRejectNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @Test
    void shouldRejectBlankEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Email("   "));
    }

    @Test
    void shouldRejectInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Email("invalid-email"));
    }

    @Test
    void equalEmailsShouldBeEqual() {
        Email email1 = new Email("john.doe@example.com");
        Email email2 = new Email("JOHN.DOE@EXAMPLE.COM");

        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }
}