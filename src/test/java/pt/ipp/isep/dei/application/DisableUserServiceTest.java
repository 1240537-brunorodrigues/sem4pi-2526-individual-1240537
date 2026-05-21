package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DisableUserServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private DisableUserService service;

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

        service = new DisableUserService(userRepository, authorizationService);
    }

    @Test
    void shouldDisableExistingUser() {
        var user = service.disableUser("admin@alsafe.pt");

        assertTrue(user.isDisabled());
        assertTrue(userRepository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow().isDisabled());
    }

    @Test
    void shouldRejectUnknownUser() {
        assertThrows(IllegalArgumentException.class,
                () -> service.disableUser("missing@alsafe.pt"));
    }

    @Test
    void shouldRejectInvalidEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> service.disableUser("invalid-email"));
    }

    @Test
    void shouldRejectDisableUserWhenNotAuthenticated() {
        session.logout();

        assertThrows(SecurityException.class, () -> service.disableUser("admin@alsafe.pt"));
    }

    @Test
    void shouldRejectDisableUserWhenUserDoesNotHavePermission() {
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

        authenticationService.logout();
        authenticationService.authenticate("weather@alsafe.pt", "Password123");

        assertThrows(SecurityException.class, () -> service.disableUser("admin@alsafe.pt"));
    }

    @Test
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new DisableUserService(null, authorizationService));
    }

    @Test
    void shouldRejectNullAuthorizationService() {
        assertThrows(IllegalArgumentException.class,
                () -> new DisableUserService(userRepository, null));
    }
}