package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.RegisterUserRequest;
import pt.ipp.isep.dei.application.RegisterUserService;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserControllerTest {

    private RegisterUserController controller;

    @BeforeEach
    void setUp() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        RegisterUserService service = new RegisterUserService(userRepository, roleRepository);
        controller = new RegisterUserController(service);
    }

    @Test
    void shouldRegisterUserThroughController() {
        RegisterUserRequest request = new RegisterUserRequest(
                "john.doe@alsafe.pt",
                "John Doe",
                "+351912345678",
                "Password123",
                Set.of("BACKOFFICE_OPERATOR"),
                LocalDate.of(2028, 12, 31),
                LocalDate.of(2026, 1, 1),
                12
        );

        User user = controller.registerUser(request);

        assertEquals(new Email("john.doe@alsafe.pt"), user.email());
        assertEquals("John Doe", user.name());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class, () -> new RegisterUserController(null));
    }
}