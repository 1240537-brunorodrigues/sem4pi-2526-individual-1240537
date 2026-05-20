package pt.ipp.isep.dei.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStatusTest {

    @Test
    void enabledStatusShouldBeEnabled() {
        assertTrue(UserStatus.ENABLED.isEnabled());
        assertFalse(UserStatus.ENABLED.isDisabled());
    }

    @Test
    void disabledStatusShouldBeDisabled() {
        assertTrue(UserStatus.DISABLED.isDisabled());
        assertFalse(UserStatus.DISABLED.isEnabled());
    }
}