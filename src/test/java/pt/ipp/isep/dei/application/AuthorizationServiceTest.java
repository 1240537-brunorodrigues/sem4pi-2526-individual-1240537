package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationServiceTest {

    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        AuthenticatedUserSession session = new AuthenticatedUserSession();

        authenticationService = new AuthenticationService(userRepository, session);
        authorizationService = new AuthorizationService(session);
    }

    @Test
    void shouldAuthorizeLoggedInUserWithPermission() {
        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        assertTrue(authorizationService.isAuthorized("REGISTER_USER"));
    }

    @Test
    void shouldNotAuthorizeUserWithoutPermission() {
        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        assertFalse(authorizationService.isAuthorized("CREATE_FLIGHT_PLAN"));
    }

    @Test
    void shouldNotAuthorizeWhenNoUserIsLoggedIn() {
        assertFalse(authorizationService.isAuthorized("REGISTER_USER"));
    }

    @Test
    void shouldNotAuthorizeDisabledCurrentUser() {
        var user = authenticationService.authenticate("admin@alsafe.pt", "Password123");
        user.disable();

        assertFalse(authorizationService.isAuthorized("REGISTER_USER"));
    }

    @Test
    void shouldNotThrowWhenUserHasPermission() {
        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        assertDoesNotThrow(() -> authorizationService.requirePermission("REGISTER_USER"));
    }

    @Test
    void shouldThrowWhenUserDoesNotHavePermission() {
        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        assertThrows(SecurityException.class,
                () -> authorizationService.requirePermission("CREATE_FLIGHT_PLAN"));
    }

    @Test
    void shouldThrowWhenNoUserIsLoggedIn() {
        assertThrows(SecurityException.class,
                () -> authorizationService.requirePermission("REGISTER_USER"));
    }

    @Test
    void shouldRejectNullSession() {
        assertThrows(IllegalArgumentException.class,
                () -> new AuthorizationService(null));
    }
}