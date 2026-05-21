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

class DisableUserServiceTest {

    private UserRepository userRepository;
    private DisableUserService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        service = new DisableUserService(userRepository);
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
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new DisableUserService(null));
    }
}