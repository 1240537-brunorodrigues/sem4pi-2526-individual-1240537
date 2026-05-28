package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftManufacturer;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftType;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateAircraftModelServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AircraftModelRepository aircraftModelRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private CreateAircraftModelService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();
        aircraftModelRepository = new InMemoryAircraftModelRepository();

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

        service = new CreateAircraftModelService(
                aircraftModelRepository,
                authorizationService
        );
    }

    private CreateAircraftModelRequest validRequest(String modelName, String manufacturerName) {
        return new CreateAircraftModelRequest(
                modelName,
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturerName,
                "United States",
                "WIDE_BODY"
        );
    }

    @Test
    void shouldCreateValidAircraftModel() {
        var aircraftModel = service.createAircraftModel(validRequest("777-300ER", "Boeing"));

        assertEquals("777-300ER", aircraftModel.modelName());
        assertEquals(167829, aircraftModel.emptyWeight());
        assertEquals(351534, aircraftModel.maximumTakeOffWeight());
        assertEquals(237682, aircraftModel.maximumZeroFuelWeight());
        assertEquals(181280, aircraftModel.maximumFuelCapacity());
        assertEquals(13140, aircraftModel.serviceCeiling());
        assertEquals(905, aircraftModel.cruiseSpeed());
        assertEquals(436.8, aircraftModel.wingArea());
        assertEquals(0.021, aircraftModel.dragCoefficient());
        assertEquals(1.5, aircraftModel.liftCoefficient());
        assertEquals(396, aircraftModel.capacity());
        assertEquals(new AircraftManufacturer("Boeing", "United States"), aircraftModel.manufacturer());
        assertEquals(new AircraftType("WIDE_BODY"), aircraftModel.aircraftType());
        assertTrue(aircraftModelRepository.existsByModelNameAndManufacturerName("777-300ER", "Boeing"));
    }

    @Test
    void shouldRejectNullRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(null));
    }

    @Test
    void shouldRejectDuplicatedModelNameAndManufacturer() {
        service.createAircraftModel(validRequest("777-300ER", "Boeing"));

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(validRequest("777-300er", "boeing")));
    }

    @Test
    void shouldAllowSameModelNameWithDifferentManufacturer() {
        service.createAircraftModel(validRequest("777-300ER", "Boeing"));

        var aircraftModel = service.createAircraftModel(validRequest("777-300ER", "Different Manufacturer"));

        assertEquals("777-300ER", aircraftModel.modelName());
        assertEquals(new AircraftManufacturer("Different Manufacturer", "United States"), aircraftModel.manufacturer());
    }

    @Test
    void shouldRejectBlankModelName() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(validRequest("   ", "Boeing")));
    }

    @Test
    void shouldRejectBlankManufacturerName() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(validRequest("777-300ER", "   ")));
    }

    @Test
    void shouldRejectBlankManufacturerCountry() {
        CreateAircraftModelRequest request = new CreateAircraftModelRequest(
                "777-300ER",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                "Boeing",
                "   ",
                "WIDE_BODY"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(request));
    }

    @Test
    void shouldRejectBlankAircraftType() {
        CreateAircraftModelRequest request = new CreateAircraftModelRequest(
                "777-300ER",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                "Boeing",
                "United States",
                "   "
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(request));
    }

    @Test
    void shouldRejectInvalidWeightHierarchyWhenEmptyWeightIsTooHigh() {
        CreateAircraftModelRequest request = new CreateAircraftModelRequest(
                "777-300ER",
                250000,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                "Boeing",
                "United States",
                "WIDE_BODY"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(request));
    }

    @Test
    void shouldRejectInvalidWeightHierarchyWhenZeroFuelWeightIsTooHigh() {
        CreateAircraftModelRequest request = new CreateAircraftModelRequest(
                "777-300ER",
                167829,
                351534,
                400000,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                "Boeing",
                "United States",
                "WIDE_BODY"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(request));
    }

    @Test
    void shouldRejectNonPositiveEmptyWeight() {
        CreateAircraftModelRequest request = new CreateAircraftModelRequest(
                "777-300ER",
                0,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                "Boeing",
                "United States",
                "WIDE_BODY"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(request));
    }

    @Test
    void shouldRejectNonPositiveCapacity() {
        CreateAircraftModelRequest request = new CreateAircraftModelRequest(
                "777-300ER",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                0,
                "Boeing",
                "United States",
                "WIDE_BODY"
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.createAircraftModel(request));
    }

    @Test
    void shouldRejectWhenNotAuthenticated() {
        session.logout();

        assertThrows(SecurityException.class,
                () -> service.createAircraftModel(validRequest("777-300ER", "Boeing")));
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
                () -> service.createAircraftModel(validRequest("777-300ER", "Boeing")));
    }

    @Test
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAircraftModelService(null, authorizationService));
    }

    @Test
    void shouldRejectNullAuthorizationService() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAircraftModelService(aircraftModelRepository, null));
    }
}