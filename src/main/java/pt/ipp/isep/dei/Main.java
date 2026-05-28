package pt.ipp.isep.dei;

import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.controller.*;
import pt.ipp.isep.dei.repository.*;
import pt.ipp.isep.dei.ui.console.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();
        AirControlAreaRepository airControlAreaRepository = new InMemoryAirControlAreaRepository();
        AirportRepository airportRepository = new InMemoryAirportRepository();
        AircraftEngineModelRepository aircraftEngineModelRepository =
                new InMemoryAircraftEngineModelRepository();
        AircraftModelRepository aircraftModelRepository =
                new InMemoryAircraftModelRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        AuthenticatedUserSession session = new AuthenticatedUserSession();

        AuthenticationService authenticationService = new AuthenticationService(userRepository, session);
        AuthorizationService authorizationService = new AuthorizationService(session);

        RegisterUserService registerUserService = new RegisterUserService(
                userRepository,
                roleRepository,
                authorizationService
        );

        ListUsersService listUsersService = new ListUsersService(
                userRepository,
                authorizationService
        );

        EnableUserService enableUserService = new EnableUserService(
                userRepository,
                authorizationService
        );

        DisableUserService disableUserService = new DisableUserService(
                userRepository,
                authorizationService
        );

        RegisterAirControlAreaService registerAirControlAreaService = new RegisterAirControlAreaService(
                airControlAreaRepository,
                authorizationService
        );

        CreateAirportService createAirportService = new CreateAirportService(
                airportRepository,
                airControlAreaRepository,
                authorizationService
        );

        CreateAircraftEngineModelService createAircraftEngineModelService =
                new CreateAircraftEngineModelService(
                        aircraftEngineModelRepository,
                        authorizationService
                );

        CreateAircraftModelService createAircraftModelService =
                new CreateAircraftModelService(
                        aircraftModelRepository,
                        authorizationService
                );

        AuthenticationController authenticationController = new AuthenticationController(authenticationService);
        RegisterUserController registerUserController = new RegisterUserController(registerUserService);
        ListUsersController listUsersController = new ListUsersController(listUsersService);
        EnableUserController enableUserController = new EnableUserController(enableUserService);
        DisableUserController disableUserController = new DisableUserController(disableUserService);
        RegisterAirControlAreaController registerAirControlAreaController =
                new RegisterAirControlAreaController(registerAirControlAreaService);
        CreateAirportController createAirportController =
                new CreateAirportController(createAirportService);
        CreateAircraftEngineModelController createAircraftEngineModelController =
                new CreateAircraftEngineModelController(createAircraftEngineModelService);
        CreateAircraftModelController createAircraftModelController =
                new CreateAircraftModelController(createAircraftModelService);

        Scanner scanner = new Scanner(System.in);

        AuthenticationUI authenticationUI = new AuthenticationUI(authenticationController, scanner);
        RegisterUserUI registerUserUI = new RegisterUserUI(registerUserController, scanner);
        ListUsersUI listUsersUI = new ListUsersUI(listUsersController);
        EnableDisableUserUI enableDisableUserUI = new EnableDisableUserUI(
                enableUserController,
                disableUserController,
                scanner
        );
        RegisterAirControlAreaUI registerAirControlAreaUI = new RegisterAirControlAreaUI(
                registerAirControlAreaController,
                scanner
        );
        CreateAirportUI createAirportUI = new CreateAirportUI(
                createAirportController,
                scanner
        );
        CreateAircraftEngineModelUI createAircraftEngineModelUI =
                new CreateAircraftEngineModelUI(
                        createAircraftEngineModelController,
                        scanner
                );
        CreateAircraftModelUI createAircraftModelUI =
                new CreateAircraftModelUI(
                        createAircraftModelController,
                        scanner
                );

        MainMenuUI menu = new MainMenuUI(
                authenticationUI,
                registerUserUI,
                listUsersUI,
                enableDisableUserUI,
                registerAirControlAreaUI,
                createAirportUI,
                createAircraftEngineModelUI,
                createAircraftModelUI,
                authorizationService,
                scanner
        );

        menu.run();
    }
}