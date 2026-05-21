package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class DisableUserControllerTest {

    @Test
    void shouldDisableUserThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        new Bootstrap(userRepository, roleRepository).run();

        AuthenticatedUserSession session = new AuthenticatedUserSession();
        AuthenticationService authenticationService = new AuthenticationService(userRepository, session);
        AuthorizationService authorizationService = new AuthorizationService(session);

        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        DisableUserService service = new DisableUserService(userRepository, authorizationService);
        DisableUserController controller = new DisableUserController(service);

        var user = controller.disableUser("admin@alsafe.pt");

        assertTrue(user.isDisabled());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new DisableUserController(null));
    }
}