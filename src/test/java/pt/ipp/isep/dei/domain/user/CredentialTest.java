package pt.ipp.isep.dei.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialTest {

    @Test
    void shouldCreateCredentialWithValidPassword() {
        Credential credential = new Credential("Password123");

        assertNotNull(credential.passwordHash());
    }

    @Test
    void shouldNotStoreRawPassword() {
        Credential credential = new Credential("Password123");

        assertNotEquals("Password123", credential.passwordHash());
    }

    @Test
    void shouldMatchCorrectPassword() {
        Credential credential = new Credential("Password123");

        assertTrue(credential.matches("Password123"));
    }

    @Test
    void shouldNotMatchIncorrectPassword() {
        Credential credential = new Credential("Password123");

        assertFalse(credential.matches("WrongPassword123"));
    }

    @Test
    void shouldRejectNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> new Credential(null));
    }

    @Test
    void shouldRejectBlankPassword() {
        assertThrows(IllegalArgumentException.class, () -> new Credential("   "));
    }

    @Test
    void shouldRejectShortPassword() {
        assertThrows(IllegalArgumentException.class, () -> new Credential("1234567"));
    }

    @Test
    void shouldCreateCredentialFromExistingHash() {
        Credential original = new Credential("Password123");
        Credential restored = Credential.fromHash(original.passwordHash());

        assertEquals(original, restored);
        assertTrue(restored.matches("Password123"));
    }

    @Test
    void shouldProtectCredentialToString() {
        Credential credential = new Credential("Password123");

        assertFalse(credential.toString().contains("Password123"));
        assertTrue(credential.toString().contains("[PROTECTED]"));
    }
}