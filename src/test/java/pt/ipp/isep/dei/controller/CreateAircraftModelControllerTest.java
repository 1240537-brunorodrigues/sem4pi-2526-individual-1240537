package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftManufacturer;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateAircraftModelControllerTest {

    @Test
    void shouldCreateAircraftModelThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();
        AircraftModelRepository aircraftModelRepository = new InMemoryAircraftModelRepository();

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

        CreateAircraftModelService service = new CreateAircraftModelService(
                aircraftModelRepository,
                authorizationService
        );

        CreateAircraftModelController controller = new CreateAircraftModelController(service);

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
                "WIDE_BODY"
        );

        var aircraftModel = controller.createAircraftModel(request);

        assertEquals("777-300ER", aircraftModel.modelName());
        assertEquals(new AircraftManufacturer("Boeing", "United States"), aircraftModel.manufacturer());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAircraftModelController(null));
    }
}