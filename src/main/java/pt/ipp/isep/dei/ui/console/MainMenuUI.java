package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.application.AuthorizationService;

import java.util.Scanner;

public class MainMenuUI {

    private final Scanner scanner;
    private final AuthenticationUI authenticationUI;
    private final RegisterUserUI registerUserUI;
    private final AuthorizationService authorizationService;

    public MainMenuUI(
            AuthenticationUI authenticationUI,
            RegisterUserUI registerUserUI,
            AuthorizationService authorizationService,
            Scanner scanner
    ) {
        if (authenticationUI == null) {
            throw new IllegalArgumentException("Authentication UI cannot be null.");
        }

        if (registerUserUI == null) {
            throw new IllegalArgumentException("Register user UI cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.authenticationUI = authenticationUI;
        this.registerUserUI = registerUserUI;
        this.authorizationService = authorizationService;
        this.scanner = scanner;
    }

    public void run() {
        int option;

        do {
            showMenu();
            option = readOption();

            switch (option) {
                case 1 -> authenticationUI.login();
                case 2 -> runRegisterUser();
                case 3 -> authenticationUI.logout();
                case 0 -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid option. Please try again.");
            }

            System.out.println();

        } while (option != 0);
    }

    private void runRegisterUser() {
        try {
            authorizationService.requirePermission("REGISTER_USER");
            registerUserUI.run();
        } catch (SecurityException exception) {
            System.out.println("Access denied.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private void showMenu() {
        System.out.println("=================================");
        System.out.println("        AlSafe Backoffice        ");
        System.out.println("=================================");
        System.out.println("1 - Login");
        System.out.println("2 - Register User");
        System.out.println("3 - Logout");
        System.out.println("0 - Exit");
        System.out.print("Choose an option: ");
    }

    private int readOption() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please insert a number.");
            scanner.nextLine();
            System.out.print("Choose an option: ");
        }

        int option = scanner.nextInt();
        scanner.nextLine();

        return option;
    }
}