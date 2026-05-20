package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.application.RegisterUserRequest;
import pt.ipp.isep.dei.controller.RegisterUserController;
import pt.ipp.isep.dei.domain.user.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class RegisterUserUI {

    private final RegisterUserController controller;
    private final Scanner scanner;

    public RegisterUserUI(RegisterUserController controller, Scanner scanner) {
        if (controller == null) {
            throw new IllegalArgumentException("Register user controller cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("=================================");
        System.out.println("          Register User          ");
        System.out.println("=================================");

        try {
            String email = readText("Email: ");
            String name = readText("Name: ");
            String phoneNumber = readText("Phone number: ");
            String password = readText("Password: ");

            System.out.println();
            System.out.println("Available roles:");
            System.out.println("- ADMINISTRATOR");
            System.out.println("- BACKOFFICE_OPERATOR");
            System.out.println("- WEATHER_PERSON");
            System.out.println("- FLIGHT_CONTROL_OPERATOR");
            System.out.println("- PILOT");
            System.out.println("- AIR_TRANSPORT_COMPANY_COLLABORATOR");

            String rolesInput = readText("Roles, separated by comma: ");
            Set<String> roleNames = parseRoles(rolesInput);

            LocalDate securityClearanceExpirationDate = readDate("Security clearance expiration date (yyyy-mm-dd): ");
            LocalDate skillsAssessmentDate = readDate("Skills assessment date (yyyy-mm-dd): ");
            int skillsAssessmentValidityPeriod = readInt("Skills assessment validity period in months: ");

            RegisterUserRequest request = new RegisterUserRequest(
                    email,
                    name,
                    phoneNumber,
                    password,
                    roleNames,
                    securityClearanceExpirationDate,
                    skillsAssessmentDate,
                    skillsAssessmentValidityPeriod
            );

            User user = controller.registerUser(request);

            System.out.println();
            System.out.println("User registered successfully.");
            System.out.println("Email: " + user.email());
            System.out.println("Name: " + user.name());
            System.out.println("Status: " + user.status());

        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println("Could not register user.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine());
            } catch (Exception exception) {
                System.out.println("Invalid date. Please use yyyy-mm-dd.");
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please insert an integer.");
            }
        }
    }

    private Set<String> parseRoles(String rolesInput) {
        if (rolesInput == null || rolesInput.isBlank()) {
            throw new IllegalArgumentException("At least one role must be provided.");
        }

        return Arrays.stream(rolesInput.split(","))
                .map(String::trim)
                .filter(role -> !role.isBlank())
                .collect(Collectors.toSet());
    }
}