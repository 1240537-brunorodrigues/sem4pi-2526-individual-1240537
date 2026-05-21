package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class EnableUserServiceTest {

    private UserRepository userRepository;
    private EnableUserService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        userRepository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow().disable();

        service = new EnableUserService(userRepository);
    }

    @Test
    void shouldEnableExistingUser() {
        var user = service.enableUser("admin@alsafe.pt");

        assertTrue(user.isEnabled());
        assertTrue(userRepository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow().isEnabled());
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
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new EnableUserService(null));
    }
}