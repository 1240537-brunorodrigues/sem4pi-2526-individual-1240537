package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.airport.IATACode;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateAirportControllerTest {

    @Test
    void shouldCreateAirportThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();
        AirportRepository airportRepository = new InMemoryAirportRepository();
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

        airControlAreaRepository.save(new AirControlArea(
                new AreaCode("LPPC"),
                "Lisbon FIR",
                new GeographicBoundary(List.of(
                        new Coordinate(41.0, -8.0),
                        new Coordinate(42.0, -8.0),
                        new Coordinate(42.0, -7.0)
                ))
        ));

        CreateAirportService service = new CreateAirportService(
                airportRepository,
                airControlAreaRepository,
                authorizationService
        );

        CreateAirportController controller = new CreateAirportController(service);

        CreateAirportRequest request = new CreateAirportRequest(
                "Francisco Sá Carneiro Airport",
                "Porto",
                "Portugal",
                "PT",
                69,
                "OPO",
                "LPPR",
                41.2481,
                -8.6814,
                "LPPC"
        );

        var airport = controller.createAirport(request);

        assertEquals(new IATACode("OPO"), airport.iataCode());
        assertEquals("Porto", airport.town());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAirportController(null));
    }
}