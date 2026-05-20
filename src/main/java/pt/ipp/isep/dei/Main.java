package pt.ipp.isep.dei;

import pt.ipp.isep.dei.application.RegisterUserService;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.controller.RegisterUserController;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;
import pt.ipp.isep.dei.ui.console.MainMenuUI;
import pt.ipp.isep.dei.ui.console.RegisterUserUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        RegisterUserService registerUserService = new RegisterUserService(userRepository, roleRepository);
        RegisterUserController registerUserController = new RegisterUserController(registerUserService);

        Scanner scanner = new Scanner(System.in);
        RegisterUserUI registerUserUI = new RegisterUserUI(registerUserController, scanner);

        MainMenuUI menu = new MainMenuUI(registerUserUI, scanner);
        menu.run();
    }
}
