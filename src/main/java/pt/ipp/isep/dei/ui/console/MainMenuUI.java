package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.application.AuthorizationService;

import java.util.Scanner;

public class MainMenuUI {

    private final Scanner scanner;
    private final AuthenticationUI authenticationUI;
    private final RegisterUserUI registerUserUI;
    private final ListUsersUI listUsersUI;
    private final EnableDisableUserUI enableDisableUserUI;
    private final AuthorizationService authorizationService;

    public MainMenuUI(
            AuthenticationUI authenticationUI,
            RegisterUserUI registerUserUI,
            ListUsersUI listUsersUI,
            EnableDisableUserUI enableDisableUserUI,
            AuthorizationService authorizationService,
            Scanner scanner
    ) {
        if (authenticationUI == null) {
            throw new IllegalArgumentException("Authentication UI cannot be null.");
        }

        if (registerUserUI == null) {
            throw new IllegalArgumentException("Register user UI cannot be null.");
        }

        if (listUsersUI == null) {
            throw new IllegalArgumentException("List users UI cannot be null.");
        }

        if (enableDisableUserUI == null) {
            throw new IllegalArgumentException("Enable/disable user UI cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.authenticationUI = authenticationUI;
        this.registerUserUI = registerUserUI;
        this.listUsersUI = listUsersUI;
        this.enableDisableUserUI = enableDisableUserUI;
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
                case 4 -> runListUsers();
                case 5 -> runEnableUser();
                case 6 -> runDisableUser();
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

    private void runListUsers() {
        try {
            authorizationService.requirePermission("LIST_USERS");
            listUsersUI.run();
        } catch (SecurityException exception) {
            System.out.println("Access denied.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private void runEnableUser() {
        try {
            authorizationService.requirePermission("ENABLE_USER");
            enableDisableUserUI.enableUser();
        } catch (SecurityException exception) {
            System.out.println("Access denied.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private void runDisableUser() {
        try {
            authorizationService.requirePermission("DISABLE_USER");
            enableDisableUserUI.disableUser();
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
        System.out.println("4 - List Users");
        System.out.println("5 - Enable User");
        System.out.println("6 - Disable User");
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