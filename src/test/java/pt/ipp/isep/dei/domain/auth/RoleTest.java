package pt.ipp.isep.dei.domain.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void shouldCreateValidRole() {
        Role role = new Role("ADMINISTRATOR");

        assertEquals("ADMINISTRATOR", role.name());
    }

    @Test
    void shouldNormalizeRoleName() {
        Role role = new Role("backoffice operator");

        assertEquals("BACKOFFICE_OPERATOR", role.name());
    }

    @Test
    void shouldRejectNullRoleName() {
        assertThrows(IllegalArgumentException.class, () -> new Role(null));
    }

    @Test
    void shouldRejectBlankRoleName() {
        assertThrows(IllegalArgumentException.class, () -> new Role("   "));
    }

    @Test
    void shouldAddPermissionToRole() {
        Role role = new Role("ADMINISTRATOR");
        Permission permission = new Permission("REGISTER_USER");

        role.addPermission(permission);

        assertTrue(role.hasPermission(permission));
    }

    @Test
    void shouldRejectNullPermission() {
        Role role = new Role("ADMINISTRATOR");

        assertThrows(IllegalArgumentException.class, () -> role.addPermission(null));
    }

    @Test
    void shouldReturnFalseWhenCheckingNullPermission() {
        Role role = new Role("ADMINISTRATOR");

        assertFalse(role.hasPermission(null));
    }

    @Test
    void shouldNotDuplicatePermissions() {
        Role role = new Role("ADMINISTRATOR");
        Permission permission1 = new Permission("REGISTER_USER");
        Permission permission2 = new Permission("register user");

        role.addPermission(permission1);
        role.addPermission(permission2);

        assertEquals(1, role.permissions().size());
    }

    @Test
    void permissionsCollectionShouldBeImmutable() {
        Role role = new Role("ADMINISTRATOR");
        Permission permission = new Permission("REGISTER_USER");

        role.addPermission(permission);

        assertThrows(UnsupportedOperationException.class,
                () -> role.permissions().add(new Permission("DISABLE_USER")));
    }

    @Test
    void equalRolesShouldBeEqual() {
        Role role1 = new Role("BACKOFFICE_OPERATOR");
        Role role2 = new Role("backoffice operator");

        assertEquals(role1, role2);
        assertEquals(role1.hashCode(), role2.hashCode());
    }
}