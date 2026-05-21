package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAirControlAreaControllerTest {

    @Test
    void shouldRegisterAirControlAreaThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();
        AirControlAreaRepository airControlAreaRepository = new InMemoryAirControlAreaRepository();

        new Bootstrap(userRepository, roleRepository).run();

        AuthenticatedUserSession session = new AuthenticatedUserSession();
        AuthenticationService authenticationService = new AuthenticationService(userRepository, session);
        AuthorizationService authorizationService = new AuthorizationService(session);

        User backofficeOperator = new User(
                new Email("backoffice@alsafe.pt"),
                "Backoffice Operator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(roleRepository.findByName("BACKOFFICE_OPERATOR").orElseThrow()),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        userRepository.save(backofficeOperator);

        authenticationService.authenticate("backoffice@alsafe.pt", "Password123");

        RegisterAirControlAreaService service = new RegisterAirControlAreaService(
                airControlAreaRepository,
                authorizationService
        );

        RegisterAirControlAreaController controller = new RegisterAirControlAreaController(service);

        RegisterAirControlAreaRequest request = new RegisterAirControlAreaRequest(
                "LPPC",
                "Lisbon FIR",
                List.of(
                        new RegisterAirControlAreaRequest.CoordinateRequest(41.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -7.0)
                )
        );

        var area = controller.registerAirControlArea(request);

        assertEquals(new AreaCode("LPPC"), area.code());
        assertEquals("Lisbon FIR", area.name());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new RegisterAirControlAreaController(null));
    }
}