package pt.ipp.isep.dei;

import pt.ipp.isep.dei.application.*;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.controller.AuthenticationController;
import pt.ipp.isep.dei.controller.ListUsersController;
import pt.ipp.isep.dei.controller.RegisterUserController;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;
import pt.ipp.isep.dei.ui.console.AuthenticationUI;
import pt.ipp.isep.dei.ui.console.ListUsersUI;
import pt.ipp.isep.dei.ui.console.MainMenuUI;
import pt.ipp.isep.dei.ui.console.RegisterUserUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        AuthenticatedUserSession session = new AuthenticatedUserSession();

        AuthenticationService authenticationService = new AuthenticationService(userRepository, session);
        AuthorizationService authorizationService = new AuthorizationService(session);
        RegisterUserService registerUserService = new RegisterUserService(userRepository, roleRepository);
        ListUsersService listUsersService = new ListUsersService(userRepository);

        AuthenticationController authenticationController = new AuthenticationController(authenticationService);
        RegisterUserController registerUserController = new RegisterUserController(registerUserService);
        ListUsersController listUsersController = new ListUsersController(listUsersService);

        Scanner scanner = new Scanner(System.in);

        AuthenticationUI authenticationUI = new AuthenticationUI(authenticationController, scanner);
        RegisterUserUI registerUserUI = new RegisterUserUI(registerUserController, scanner);
        ListUsersUI listUsersUI = new ListUsersUI(listUsersController);

        MainMenuUI menu = new MainMenuUI(
                authenticationUI,
                registerUserUI,
                listUsersUI,
                authorizationService,
                scanner
        );

        menu.run();
    }
}