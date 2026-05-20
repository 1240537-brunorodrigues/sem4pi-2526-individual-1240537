package pt.ipp.isep.dei.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void shouldCreateValidPortuguesePhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("912345678");

        assertEquals("912345678", phoneNumber.value());
    }

    @Test
    void shouldCreateValidInternationalPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("+351912345678");

        assertEquals("+351912345678", phoneNumber.value());
    }

    @Test
    void shouldRemoveSpaces() {
        PhoneNumber phoneNumber = new PhoneNumber("+351 912 345 678");

        assertEquals("+351912345678", phoneNumber.value());
    }

    @Test
    void shouldRemoveHyphens() {
        PhoneNumber phoneNumber = new PhoneNumber("+351-912-345-678");

        assertEquals("+351912345678", phoneNumber.value());
    }

    @Test
    void shouldRejectNullPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(null));
    }

    @Test
    void shouldRejectBlankPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("   "));
    }

    @Test
    void shouldRejectLetters() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("912ABC678"));
    }

    @Test
    void shouldRejectTooShortNumber() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("12345"));
    }

    @Test
    void equalPhoneNumbersShouldBeEqual() {
        PhoneNumber phoneNumber1 = new PhoneNumber("+351 912 345 678");
        PhoneNumber phoneNumber2 = new PhoneNumber("+351912345678");

        assertEquals(phoneNumber1, phoneNumber2);
        assertEquals(phoneNumber1.hashCode(), phoneNumber2.hashCode());
    }
}