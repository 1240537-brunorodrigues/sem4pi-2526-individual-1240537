package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AuthenticationController;
import pt.ipp.isep.dei.domain.user.User;

import java.util.Scanner;

public class AuthenticationUI {

    private final AuthenticationController controller;
    private final Scanner scanner;

    public AuthenticationUI(AuthenticationController controller, Scanner scanner) {
        if (controller == null) {
            throw new IllegalArgumentException("Authentication controller cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.controller = controller;
        this.scanner = scanner;
    }

    public void login() {
        System.out.println("=================================");
        System.out.println("              Login              ");
        System.out.println("=================================");

        try {
            String email = readText("Email: ");
            String password = readText("Password: ");

            User user = controller.login(email, password);

            System.out.println();
            System.out.println("Login successful.");
            System.out.println("Current user: " + user.name());
            System.out.println("Email: " + user.email());

        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println("Login failed.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    public void logout() {
        if (!controller.isLoggedIn()) {
            System.out.println("No user is currently logged in.");
            return;
        }

        User currentUser = controller.currentUser();
        controller.logout();

        System.out.println("User logged out successfully: " + currentUser.email());
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}