package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.DisableUserController;
import pt.ipp.isep.dei.controller.EnableUserController;
import pt.ipp.isep.dei.domain.user.User;

import java.util.Scanner;

public class EnableDisableUserUI {

    private final EnableUserController enableUserController;
    private final DisableUserController disableUserController;
    private final Scanner scanner;

    public EnableDisableUserUI(
            EnableUserController enableUserController,
            DisableUserController disableUserController,
            Scanner scanner
    ) {
        if (enableUserController == null) {
            throw new IllegalArgumentException("Enable user controller cannot be null.");
        }

        if (disableUserController == null) {
            throw new IllegalArgumentException("Disable user controller cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.enableUserController = enableUserController;
        this.disableUserController = disableUserController;
        this.scanner = scanner;
    }

    public void enableUser() {
        System.out.println("=================================");
        System.out.println("            Enable User          ");
        System.out.println("=================================");

        String email = readText("User email: ");

        try {
            User user = enableUserController.enableUser(email);
            System.out.println("User enabled successfully: " + user.email());
        } catch (IllegalArgumentException exception) {
            System.out.println("Could not enable user.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    public void disableUser() {
        System.out.println("=================================");
        System.out.println("           Disable User          ");
        System.out.println("=================================");

        String email = readText("User email: ");

        try {
            User user = disableUserController.disableUser(email);
            System.out.println("User disabled successfully: " + user.email());
        } catch (IllegalArgumentException exception) {
            System.out.println("Could not disable user.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}