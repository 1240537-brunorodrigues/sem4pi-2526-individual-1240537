package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.*;
import java.time.LocalDate;
import java.util.Set;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        session = new AuthenticatedUserSession();
        authenticationService = new AuthenticationService(userRepository, session);
    }

    @Test
    void shouldAuthenticateValidUser() {
        User user = authenticationService.authenticate("admin@alsafe.pt", "Password123");

        assertEquals(new Email("admin@alsafe.pt"), user.email());
        assertTrue(authenticationService.isLoggedIn());
        assertEquals(user, authenticationService.currentUser());
    }

    @Test
    void shouldRejectUnknownEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> authenticationService.authenticate("missing@alsafe.pt", "Password123"));

        assertFalse(authenticationService.isLoggedIn());
    }

    @Test
    void shouldRejectWrongPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> authenticationService.authenticate("admin@alsafe.pt", "WrongPassword123"));

        assertFalse(authenticationService.isLoggedIn());
    }

    @Test
    void shouldRejectDisabledUser() {
        User admin = userRepository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow();
        admin.disable();

        assertThrows(IllegalArgumentException.class,
                () -> authenticationService.authenticate("admin@alsafe.pt", "Password123"));

        assertFalse(authenticationService.isLoggedIn());
    }

    @Test
    void shouldLogoutAuthenticatedUser() {
        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        authenticationService.logout();

        assertFalse(authenticationService.isLoggedIn());
    }

    @Test
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new AuthenticationService(null, session));
    }

    @Test
    void shouldRejectNullSession() {
        assertThrows(IllegalArgumentException.class,
                () -> new AuthenticationService(userRepository, null));
    }

    @Test
    void shouldRejectUserWithExpiredSecurityClearance() {
        Role role = roleRepository.findByName("ADMINISTRATOR").orElseThrow();

        User user = new User(
                new Email("expired.clearance@alsafe.pt"),
                "Expired Clearance User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.now().minusDays(1)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        userRepository.save(user);

        assertThrows(IllegalArgumentException.class,
                () -> authenticationService.authenticate("expired.clearance@alsafe.pt", "Password123"));

        assertFalse(authenticationService.isLoggedIn());
    }

    @Test
    void shouldRejectUserWithExpiredSkillsAssessment() {
        Role role = roleRepository.findByName("ADMINISTRATOR").orElseThrow();

        User user = new User(
                new Email("expired.skills@alsafe.pt"),
                "Expired Skills User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.now().plusYears(1)),
                new SkillsAssessment(LocalDate.now().minusMonths(13), 12)
        );

        userRepository.save(user);

        assertThrows(IllegalArgumentException.class,
                () -> authenticationService.authenticate("expired.skills@alsafe.pt", "Password123"));

        assertFalse(authenticationService.isLoggedIn());
    }
}