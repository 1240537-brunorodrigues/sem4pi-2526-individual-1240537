package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EnableUserControllerTest {

    @Test
    void shouldEnableUserThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        new Bootstrap(userRepository, roleRepository).run();

        AuthenticatedUserSession session = new AuthenticatedUserSession();
        AuthenticationService authenticationService = new AuthenticationService(userRepository, session);
        AuthorizationService authorizationService = new AuthorizationService(session);

        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        Role role = roleRepository.findByName("BACKOFFICE_OPERATOR").orElseThrow();

        User disabledUser = new User(
                new Email("disabled.user@alsafe.pt"),
                "Disabled User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        disabledUser.disable();
        userRepository.save(disabledUser);

        EnableUserService service = new EnableUserService(userRepository, authorizationService);
        EnableUserController controller = new EnableUserController(service);

        var user = controller.enableUser("disabled.user@alsafe.pt");

        assertTrue(user.isEnabled());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new EnableUserController(null));
    }
}