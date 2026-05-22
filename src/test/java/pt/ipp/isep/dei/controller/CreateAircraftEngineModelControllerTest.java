package pt.ipp.isep.dei.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineManufacturer;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.*;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateAircraftEngineModelControllerTest {

    @Test
    void shouldCreateAircraftEngineModelThroughController() {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();
        AircraftEngineModelRepository aircraftEngineModelRepository = new InMemoryAircraftEngineModelRepository();

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

        CreateAircraftEngineModelService service = new CreateAircraftEngineModelService(
                aircraftEngineModelRepository,
                authorizationService
        );

        CreateAircraftEngineModelController controller = new CreateAircraftEngineModelController(service);

        CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                "Trent 1000",
                331.0,
                0.92,
                "Rolls-Royce",
                "United Kingdom",
                "TURBOFAN",
                "JET_A1"
        );

        var engineModel = controller.createAircraftEngineModel(request);

        assertEquals("Trent 1000", engineModel.modelName());
        assertEquals(new EngineManufacturer("Rolls-Royce", "United Kingdom"), engineModel.manufacturer());
    }

    @Test
    void shouldRejectNullService() {
        assertThrows(IllegalArgumentException.class,
                () -> new CreateAircraftEngineModelController(null));
    }
}