package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.AuthenticatedUserSession;
import pt.ipp.isep.dei.application.AuthenticationService;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationControllerTest {

    private AuthenticationController controller;

    @BeforeEach
    void setUp() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        AuthenticatedUserSession session = new AuthenticatedUserSession();
        AuthenticationService service = new AuthenticationService(userRepository, session);

        controller = new AuthenticationController(service);
    }

    @Test
    void shouldLoginThroughController() {
        var user = controller.login("admin@alsafe.pt", "Password123");

        assertEquals(new Email("admin@alsafe.pt"), user.email());
        assertTrue(controller.isLoggedIn());
    }

    @Test
    void shouldLogoutThroughController() {
        controller.login("admin@alsafe.pt", "Password123");

        controller.logout();

        assertFalse(controller.isLoggedIn());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new AuthenticationController(null));
    }
}