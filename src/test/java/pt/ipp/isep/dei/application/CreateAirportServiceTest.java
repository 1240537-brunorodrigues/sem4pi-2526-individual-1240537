package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.airport.IATACode;
import pt.ipp.isep.dei.domain.airport.ICAOCode;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateAirportServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AirportRepository airportRepository;
    private AirControlAreaRepository airControlAreaRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private CreateAirportService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();
        airportRepository = new InMemoryAirportRepository();
        airControlAreaRepository = new InMemoryAirControlAreaRepository();

        new Bootstrap(userRepository, roleRepository).run();

        session = new AuthenticatedUserSession();
        authenticationService = new AuthenticationService(userRepository, session);
        authorizationService = new AuthorizationService(session);

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

        AirControlArea area = new AirControlArea(
                new AreaCode("LPPC"),
                "Lisbon FIR",
                new GeographicBoundary(List.of(
                        new Coordinate(41.0, -8.0),
                        new Coordinate(42.0, -8.0),
                        new Coordinate(42.0, -7.0)
                ))
        );

        airControlAreaRepository.save(area);

        service = new CreateAirportService(
                airportRepository,
                airControlAreaRepository,
                authorizationService
        );
    }

    private CreateAirportRequest validRequest(String iataCode, String icaoCode) {
        return new CreateAirportRequest(
                "Francisco Sá Carneiro Airport",
                "Porto",
                "Portugal",
                "PT",
                69,
                iataCode,
                icaoCode,
                41.2481,
                -8.6814,
                "LPPC"
        );
    }

    @Test
    void shouldCreateValidAirport() {
        var airport = service.createAirport(validRequest("OPO", "LPPR"));

        assertEquals("Francisco Sá Carneiro Airport", airport.name());
        assertEquals("Porto", airport.town());
        assertEquals(new IATACode("OPO"), airport.iataCode());
        assertEquals(new ICAOCode("LPPR"), airport.icaoCode());
        assertEquals(new AreaCode("LPPC"), airport.airControlArea().code());
        assertTrue(airportRepository.existsByIATACode(new IATACode("OPO")));
    }

    @Test
    void shouldRejectNullRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(null));
    }

    @Test
    void shouldRejectDuplicatedIATACode() {
        service.createAirport(validRequest("OPO", "LPPR"));

        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(validRequest("opo", "LPPT")));
    }

    @Test
    void shouldRejectDuplicatedICAOCode() {
        service.createAirport(validRequest("OPO", "LPPR"));

        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(validRequest("LIS", "lppr")));
    }

    @Test
    void shouldRejectUnknownAirControlArea() {
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
                "LAAA"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(request));
    }

    @Test
    void shouldRejectInvalidIATACode() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(validRequest("OP1", "LPPR")));
    }

    @Test
    void shouldRejectInvalidICAOCode() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(validRequest("OPO", "LPP1")));
    }

    @Test
    void shouldRejectInvalidCountryCode() {
        CreateAirportRequest request = new CreateAirportRequest(
                "Francisco Sá Carneiro Airport",
                "Porto",
                "Portugal",
                "PRT",
                69,
                "OPO",
                "LPPR",
                41.2481,
                -8.6814,
                "LPPC"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(request));
    }

    @Test
    void shouldRejectInvalidCoordinate() {
        CreateAirportRequest request = new CreateAirportRequest(
                "Francisco Sá Carneiro Airport",
                "Porto",
                "Portugal",
                "PT",
                69,
                "OPO",
                "LPPR",
                91,
                -8.6814,
                "LPPC"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(request));
    }

    @Test
    void shouldRejectNegativeElevation() {
        CreateAirportRequest request = new CreateAirportRequest(
                "Francisco Sá Carneiro Airport",
                "Porto",
                "Portugal",
                "PT",
                -1,
                "OPO",
                "LPPR",
                41.2481,
                -8.6814,
                "LPPC"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAirport(request));
    }

    @Test
    void shouldRejectWhenNotAuthenticated() {
        session.logout();

        assertThrows(SecurityException.class,
                () -> service.createAirport(validRequest("OPO", "LPPR")));
    }

    @Test
    void shouldRejectWhenUserDoesNotHavePermission() {
        User weatherUser = new User(
                new Email("weather@alsafe.pt"),
                "Weather User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(roleRepository.findByName("WEATHER_PERSON").orElseThrow()),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        userRepository.save(weatherUser);

        authenticationService.logout();
        authenticationService.authenticate("weather@alsafe.pt", "Password123");

        assertThrows(SecurityException.class,
                () -> service.createAirport(validRequest("OPO", "LPPR")));
    }

    @Test
    void shouldRejectNullAirportRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAirportService(null, airControlAreaRepository, authorizationService));
    }

    @Test
    void shouldRejectNullAirControlAreaRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAirportService(airportRepository, null, authorizationService));
    }

    @Test
    void shouldRejectNullAuthorizationService() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAirportService(airportRepository, airControlAreaRepository, null));
    }
}