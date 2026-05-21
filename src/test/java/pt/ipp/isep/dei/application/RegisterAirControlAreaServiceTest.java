package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAirControlAreaServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AirControlAreaRepository airControlAreaRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private RegisterAirControlAreaService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();
        airControlAreaRepository = new InMemoryAirControlAreaRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        session = new AuthenticatedUserSession();
        authenticationService = new AuthenticationService(userRepository, session);
        authorizationService = new AuthorizationService(session);

        authenticationService.authenticate("admin@alsafe.pt", "Password123");

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

        authenticationService.logout();
        authenticationService.authenticate("backoffice@alsafe.pt", "Password123");

        service = new RegisterAirControlAreaService(
                airControlAreaRepository,
                authorizationService
        );
    }

    private RegisterAirControlAreaRequest validRequest(String code) {
        return new RegisterAirControlAreaRequest(
                code,
                "Lisbon FIR",
                List.of(
                        new RegisterAirControlAreaRequest.CoordinateRequest(41.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -7.0)
                )
        );
    }

    @Test
    void shouldRegisterValidAirControlArea() {
        var area = service.registerAirControlArea(validRequest("LPPC"));

        assertEquals(new AreaCode("LPPC"), area.code());
        assertEquals("Lisbon FIR", area.name());
        assertTrue(airControlAreaRepository.existsByCode(new AreaCode("LPPC")));
    }

    @Test
    void shouldRejectNullRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerAirControlArea(null));
    }

    @Test
    void shouldRejectDuplicatedAreaCode() {
        service.registerAirControlArea(validRequest("LPPC"));

        assertThrows(IllegalArgumentException.class,
                () -> service.registerAirControlArea(validRequest("lppc")));
    }

    @Test
    void shouldRejectInvalidAreaCode() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerAirControlArea(validRequest("LP1")));
    }

    @Test
    void shouldRejectBlankName() {
        RegisterAirControlAreaRequest request = new RegisterAirControlAreaRequest(
                "LPPC",
                "   ",
                List.of(
                        new RegisterAirControlAreaRequest.CoordinateRequest(41.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -7.0)
                )
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.registerAirControlArea(request));
    }

    @Test
    void shouldRejectNullBoundaryCoordinates() {
        RegisterAirControlAreaRequest request = new RegisterAirControlAreaRequest(
                "LPPC",
                "Lisbon FIR",
                null
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.registerAirControlArea(request));
    }

    @Test
    void shouldRejectLessThanThreeBoundaryCoordinates() {
        RegisterAirControlAreaRequest request = new RegisterAirControlAreaRequest(
                "LPPC",
                "Lisbon FIR",
                List.of(
                        new RegisterAirControlAreaRequest.CoordinateRequest(41.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -8.0)
                )
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.registerAirControlArea(request));
    }

    @Test
    void shouldRejectInvalidCoordinate() {
        RegisterAirControlAreaRequest request = new RegisterAirControlAreaRequest(
                "LPPC",
                "Lisbon FIR",
                List.of(
                        new RegisterAirControlAreaRequest.CoordinateRequest(91.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -8.0),
                        new RegisterAirControlAreaRequest.CoordinateRequest(42.0, -7.0)
                )
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.registerAirControlArea(request));
    }

    @Test
    void shouldRejectWhenNotAuthenticated() {
        session.logout();

        assertThrows(SecurityException.class,
                () -> service.registerAirControlArea(validRequest("LPPC")));
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
                () -> service.registerAirControlArea(validRequest("LPPC")));
    }

    @Test
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new RegisterAirControlAreaService(null, authorizationService));
    }

    @Test
    void shouldRejectNullAuthorizationService() {
        assertThrows(IllegalArgumentException.class,
                () -> new RegisterAirControlAreaService(airControlAreaRepository, null));
    }
}