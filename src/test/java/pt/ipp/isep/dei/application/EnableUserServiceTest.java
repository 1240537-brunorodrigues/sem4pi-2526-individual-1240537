package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EnableUserServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private EnableUserService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        session = new AuthenticatedUserSession();
        authenticationService = new AuthenticationService(userRepository, session);
        authorizationService = new AuthorizationService(session);

        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        service = new EnableUserService(userRepository, authorizationService);
    }

    private User createDisabledUser(String email) {
        Role role = roleRepository.findByName("BACKOFFICE_OPERATOR").orElseThrow();

        User user = new User(
                new Email(email),
                "Disabled User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        user.disable();
        userRepository.save(user);

        return user;
    }

    @Test
    void shouldEnableExistingUser() {
        createDisabledUser("disabled.user@alsafe.pt");

        var user = service.enableUser("disabled.user@alsafe.pt");

        assertTrue(user.isEnabled());
        assertTrue(userRepository.findByEmail(new Email("disabled.user@alsafe.pt")).orElseThrow().isEnabled());
    }

    @Test
    void shouldRejectUnknownUser() {
        assertThrows(IllegalArgumentException.class,
                () -> service.enableUser("missing@alsafe.pt"));
    }

    @Test
    void shouldRejectInvalidEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> service.enableUser("invalid-email"));
    }

    @Test
    void shouldRejectEnableUserWhenNotAuthenticated() {
        createDisabledUser("disabled.user@alsafe.pt");

        session.logout();

        assertThrows(SecurityException.class,
                () -> service.enableUser("disabled.user@alsafe.pt"));
    }

    @Test
    void shouldRejectEnableUserWhenUserDoesNotHavePermission() {
        User weatherUser = new User(
                new Email("weather@alsafe.pt"),
                "Weather User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(roleRepository.findByName("WEATHER_PERSON").orElseThrow()),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        userRepository.save(weatherUser);

        createDisabledUser("disabled.user@alsafe.pt");

        authenticationService.logout();
        authenticationService.authenticate("weather@alsafe.pt", "Password123");

        assertThrows(SecurityException.class,
                () -> service.enableUser("disabled.user@alsafe.pt"));
    }

    @Test
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new EnableUserService(null, authorizationService));
    }

    @Test
    void shouldRejectNullAuthorizationService() {
        assertThrows(IllegalArgumentException.class,
                () -> new EnableUserService(userRepository, null));
    }
}