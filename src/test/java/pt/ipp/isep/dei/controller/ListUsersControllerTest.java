package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.ListUsersService;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class ListUsersControllerTest {

    @Test
    void shouldListUsersThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        ListUsersService service = new ListUsersService(userRepository);
        ListUsersController controller = new ListUsersController(service);

        var users = controller.listUsers();

        assertEquals(1, users.size());
        assertEquals(new Email("admin@alsafe.pt"), users.get(0).email());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class, () -> new ListUsersController(null));
    }
}