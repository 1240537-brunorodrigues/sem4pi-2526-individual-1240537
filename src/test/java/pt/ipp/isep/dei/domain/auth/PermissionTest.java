package pt.ipp.isep.dei.domain.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermissionTest {

    @Test
    void shouldCreateValidPermission() {
        Permission permission = new Permission("REGISTER_USER");

        assertEquals("REGISTER_USER", permission.name());
    }

    @Test
    void shouldNormalizePermissionNameToUpperCase() {
        Permission permission = new Permission("register_user");

        assertEquals("REGISTER_USER", permission.name());
    }

    @Test
    void shouldReplaceSpacesWithUnderscores() {
        Permission permission = new Permission("register user");

        assertEquals("REGISTER_USER", permission.name());
    }

    @Test
    void shouldTrimPermissionName() {
        Permission permission = new Permission("  register_user  ");

        assertEquals("REGISTER_USER", permission.name());
    }

    @Test
    void shouldRejectNullPermissionName() {
        assertThrows(IllegalArgumentException.class, () -> new Permission(null));
    }

    @Test
    void shouldRejectBlankPermissionName() {
        assertThrows(IllegalArgumentException.class, () -> new Permission("   "));
    }

    @Test
    void equalPermissionsShouldBeEqual() {
        Permission permission1 = new Permission("REGISTER_USER");
        Permission permission2 = new Permission("register user");

        assertEquals(permission1, permission2);
        assertEquals(permission1.hashCode(), permission2.hashCode());
    }
}