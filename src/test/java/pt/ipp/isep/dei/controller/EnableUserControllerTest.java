package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.EnableUserService;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class EnableUserControllerTest {

    @Test
    void shouldEnableUserThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        new Bootstrap(userRepository, roleRepository).run();

        userRepository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow().disable();

        EnableUserService service = new EnableUserService(userRepository);
        EnableUserController controller = new EnableUserController(service);

        var user = controller.enableUser("admin@alsafe.pt");

        assertTrue(user.isEnabled());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new EnableUserController(null));
    }
}