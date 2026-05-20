package pt.ipp.isep.dei.domain.user;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.auth.Permission;
import pt.ipp.isep.dei.domain.auth.Role;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User createValidUser() {
        Role role = new Role("ADMINISTRATOR");
        role.addPermission(new Permission("REGISTER_USER"));

        return new User(
                new Email("admin@alsafe.pt"),
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        );
    }

    @Test
    void shouldCreateValidUser() {
        User user = createValidUser();

        assertEquals(new Email("admin@alsafe.pt"), user.email());
        assertEquals("System Administrator", user.name());
        assertEquals(new PhoneNumber("+351912345678"), user.phoneNumber());
        assertTrue(user.isEnabled());
    }

    @Test
    void shouldTrimUserName() {
        Role role = new Role("ADMINISTRATOR");

        User user = new User(
                new Email("admin@alsafe.pt"),
                "  System Administrator  ",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        );

        assertEquals("System Administrator", user.name());
    }

    @Test
    void shouldRejectNullEmail() {
        Role role = new Role("ADMINISTRATOR");

        assertThrows(IllegalArgumentException.class, () -> new User(
                null,
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        ));
    }

    @Test
    void shouldRejectBlankName() {
        Role role = new Role("ADMINISTRATOR");

        assertThrows(IllegalArgumentException.class, () -> new User(
                new Email("admin@alsafe.pt"),
                "   ",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        ));
    }

    @Test
    void shouldRejectNullPhoneNumber() {
        Role role = new Role("ADMINISTRATOR");

        assertThrows(IllegalArgumentException.class, () -> new User(
                new Email("admin@alsafe.pt"),
                "System Administrator",
                null,
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        ));
    }

    @Test
    void shouldRejectNullCredential() {
        Role role = new Role("ADMINISTRATOR");

        assertThrows(IllegalArgumentException.class, () -> new User(
                new Email("admin@alsafe.pt"),
                "System Administrator",
                new PhoneNumber("+351912345678"),
                null,
                Set.of(role),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        ));
    }

    @Test
    void shouldRejectEmptyRoles() {
        assertThrows(IllegalArgumentException.class, () -> new User(
                new Email("admin@alsafe.pt"),
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        ));
    }

    @Test
    void shouldRejectNullRoles() {
        assertThrows(IllegalArgumentException.class, () -> new User(
                new Email("admin@alsafe.pt"),
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                null,
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        ));
    }

    @Test
    void shouldRejectNullSecurityClearance() {
        Role role = new Role("ADMINISTRATOR");

        assertThrows(IllegalArgumentException.class, () -> new User(
                new Email("admin@alsafe.pt"),
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                null,
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        ));
    }

    @Test
    void shouldRejectNullSkillsAssessment() {
        Role role = new Role("ADMINISTRATOR");

        assertThrows(IllegalArgumentException.class, () -> new User(
                new Email("admin@alsafe.pt"),
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                null
        ));
    }

    @Test
    void shouldDisableUser() {
        User user = createValidUser();

        user.disable();

        assertTrue(user.isDisabled());
        assertEquals(UserStatus.DISABLED, user.status());
    }

    @Test
    void shouldEnableUser() {
        User user = createValidUser();

        user.disable();
        user.enable();

        assertTrue(user.isEnabled());
        assertEquals(UserStatus.ENABLED, user.status());
    }

    @Test
    void shouldCheckUserRole() {
        User user = createValidUser();

        assertTrue(user.hasRole(new Role("ADMINISTRATOR")));
        assertFalse(user.hasRole(new Role("WEATHER_PERSON")));
    }

    @Test
    void shouldReturnFalseWhenCheckingNullRole() {
        User user = createValidUser();

        assertFalse(user.hasRole(null));
    }

    @Test
    void shouldCheckUserPermission() {
        User user = createValidUser();

        assertTrue(user.hasPermission(new Permission("REGISTER_USER")));
        assertFalse(user.hasPermission(new Permission("DELETE_AIRPORT")));
    }

    @Test
    void shouldReturnFalseWhenCheckingNullPermission() {
        User user = createValidUser();

        assertFalse(user.hasPermission(null));
    }

    @Test
    void shouldMatchPassword() {
        User user = createValidUser();

        assertTrue(user.matchesPassword("Password123"));
        assertFalse(user.matchesPassword("WrongPassword123"));
    }

    @Test
    void rolesCollectionShouldBeImmutable() {
        User user = createValidUser();

        assertThrows(UnsupportedOperationException.class,
                () -> user.roles().add(new Role("WEATHER_PERSON")));
    }

    @Test
    void usersWithSameEmailShouldBeEqual() {
        User user1 = createValidUser();

        Role role = new Role("BACKOFFICE_OPERATOR");

        User user2 = new User(
                new Email("ADMIN@ALSAFE.PT"),
                "Other Name",
                new PhoneNumber("+351913333333"),
                new Credential("AnotherPassword123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 6, 1), 12)
        );

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}