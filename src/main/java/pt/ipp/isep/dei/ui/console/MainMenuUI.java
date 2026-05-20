package pt.ipp.isep.dei.ui.console;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MainMenuUI {

    private final Scanner scanner;
    private final RegisterUserUI registerUserUI;

    public MainMenuUI(RegisterUserUI registerUserUI, Scanner scanner) {
        if (registerUserUI == null) {
            throw new IllegalArgumentException("RegisterUserUI cannot be null");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }

        this.registerUserUI = registerUserUI;
        this.scanner = scanner;
    }

    public void run() {
        int option;

        do {
            showMenu();
            option = readOption();

            switch (option) {
                case 1 -> System.out.println("Login selected. Not implemented yet.");
                case 2 -> registerUserUI.run();
                case 0 -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        } while (option !=0);
    }

    private void showMenu() {
        System.out.println("=================================");
        System.out.println("        AlSafe Backoffice        ");
        System.out.println("=================================");
        System.out.println("1. Login");
        System.out.println("2. Register User");
        System.out.println("0. Exit");
        System.out.println("Choose an option: ");
    }

    private int readOption() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid option. Please insert a numeric value.");
            scanner.next();
            System.out.println("Choose an option: ");
        }
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }
}
