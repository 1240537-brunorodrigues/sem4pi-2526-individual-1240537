package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineManufacturer;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineType;
import pt.ipp.isep.dei.domain.aircraft.engine.FuelType;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateAircraftEngineModelServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AircraftEngineModelRepository aircraftEngineModelRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private CreateAircraftEngineModelService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();
        aircraftEngineModelRepository = new InMemoryAircraftEngineModelRepository();

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

        service = new CreateAircraftEngineModelService(
                aircraftEngineModelRepository,
                authorizationService
        );
    }

    private CreateAircraftEngineModelRequest validRequest(String modelName, String manufacturerName) {
        return new CreateAircraftEngineModelRequest(
                modelName,
                331.0,
                0.92,
                manufacturerName,
                "United Kingdom",
                "TURBOFAN",
                "JET_A1"
        );
    }

    @Test
    void shouldCreateValidAircraftEngineModel() {
        var engineModel = service.createAircraftEngineModel(validRequest("Trent 1000", "Rolls-Royce"));

        assertEquals("Trent 1000", engineModel.modelName());
        assertEquals(331.0, engineModel.power());
        assertEquals(0.92, engineModel.efficiency());
        assertEquals(new EngineManufacturer("Rolls-Royce", "United Kingdom"), engineModel.manufacturer());
        assertEquals(new EngineType("TURBOFAN"), engineModel.engineType());
        assertEquals(new FuelType("JET_A1"), engineModel.fuelType());
        assertTrue(aircraftEngineModelRepository.existsByModelNameAndManufacturerName("Trent 1000", "Rolls-Royce"));
    }

    @Test
    void shouldRejectNullRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(null));
    }

    @Test
    void shouldRejectDuplicatedModelNameAndManufacturer() {
        service.createAircraftEngineModel(validRequest("Trent 1000", "Rolls-Royce"));

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(validRequest("trent 1000", "rolls-royce")));
    }

    @Test
    void shouldAllowSameModelNameWithDifferentManufacturer() {
        service.createAircraftEngineModel(validRequest("Trent 1000", "Rolls-Royce"));

        var engineModel = service.createAircraftEngineModel(validRequest("Trent 1000", "Different Manufacturer"));

        assertEquals("Trent 1000", engineModel.modelName());
        assertEquals(new EngineManufacturer("Different Manufacturer", "United Kingdom"), engineModel.manufacturer());
    }

    @Test
    void shouldRejectBlankModelName() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(validRequest("   ", "Rolls-Royce")));
    }

    @Test
    void shouldRejectZeroPower() {
        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                0,
                0.92,
                "Rolls-Royce",
                "United Kingdom",
                "TURBOFAN",
                "JET_A1"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(request));
    }

    @Test
    void shouldRejectNegativePower() {
        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                -1,
                0.92,
                "Rolls-Royce",
                "United Kingdom",
                "TURBOFAN",
                "JET_A1"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(request));
    }

    @Test
    void shouldRejectZeroEfficiency() {
        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                331.0,
                0,
                "Rolls-Royce",
                "United Kingdom",
                "TURBOFAN",
                "JET_A1"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(request));
    }

    @Test
    void shouldRejectNegativeEfficiency() {
        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                331.0,
                -0.1,
                "Rolls-Royce",
                "United Kingdom",
                "TURBOFAN",
                "JET_A1"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(request));
    }

    @Test
    void shouldRejectBlankManufacturerName() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(validRequest("Trent 1000", "   ")));
    }

    @Test
    void shouldRejectBlankManufacturerCountry() {
        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                331.0,
                0.92,
                "Rolls-Royce",
                "   ",
                "TURBOFAN",
                "JET_A1"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(request));
    }

    @Test
    void shouldRejectBlankEngineType() {
        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                331.0,
                0.92,
                "Rolls-Royce",
                "United Kingdom",
                "   ",
                "JET_A1"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(request));
    }

    @Test
    void shouldRejectBlankFuelType() {
        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                331.0,
                0.92,
                "Rolls-Royce",
                "United Kingdom",
                "TURBOFAN",
                "   "
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftEngineModel(request));
    }

    @Test
    void shouldRejectWhenNotAuthenticated() {
        session.logout();

        assertThrows(SecurityException.class,
                () -> service.createAircraftEngineModel(validRequest("Trent 1000", "Rolls-Royce")));
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
                () -> service.createAircraftEngineModel(validRequest("Trent 1000", "Rolls-Royce")));
    }

    @Test
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAircraftEngineModelService(null, authorizationService));
    }

    @Test
    void shouldRejectNullAuthorizationService() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAircraftEngineModelService(aircraftEngineModelRepository, null));
    }
}