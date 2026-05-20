package pt.ipp.isep.dei.bootstrap;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.auth.Permission;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class BootstrapTest {

    @Test
    void shouldInitializeBaseRoles() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);

        bootstrap.run();

        assertTrue(roleRepository.existsByName("ADMINISTRATOR"));
        assertTrue(roleRepository.existsByName("BACKOFFICE_OPERATOR"));
        assertTrue(roleRepository.existsByName("WEATHER_PERSON"));
        assertTrue(roleRepository.existsByName("FLIGHT_CONTROL_OPERATOR"));
        assertTrue(roleRepository.existsByName("PILOT"));
        assertTrue(roleRepository.existsByName("AIR_TRANSPORT_COMPANY_COLLABORATOR"));
    }

    @Test
    void shouldInitializeDefaultAdministrator() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);

        bootstrap.run();

        assertTrue(userRepository.existsByEmail(new Email("admin@alsafe.pt")));
    }

    @Test
    void defaultAdministratorShouldHaveAdministratorRole() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);

        bootstrap.run();

        var administrator = userRepository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow();

        assertTrue(administrator.hasRole(roleRepository.findByName("ADMINISTRATOR").orElseThrow()));
    }

    @Test
    void defaultAdministratorShouldHaveRegisterUserPermission() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);

        bootstrap.run();

        var administrator = userRepository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow();

        assertTrue(administrator.hasPermission(new Permission("REGISTER_USER")));
    }

    @Test
    void shouldNotDuplicateRolesWhenRunTwice() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);

        bootstrap.run();
        bootstrap.run();

        assertEquals(6, roleRepository.findAll().size());
    }

    @Test
    void shouldNotDuplicateDefaultAdministratorWhenRunTwice() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);

        bootstrap.run();
        bootstrap.run();

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void shouldRejectNullUserRepository() {
        RoleRepository roleRepository = new InMemoryRoleRepository();

        assertThrows(IllegalArgumentException.class, () -> new Bootstrap(null, roleRepository));
    }

    @Test
    void shouldRejectNullRoleRepository() {
        UserRepository userRepository = new InMemoryUserRepository();

        assertThrows(IllegalArgumentException.class, () -> new Bootstrap(userRepository, null));
    }
}