package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.application.RegisterAirControlAreaRequest;
import pt.ipp.isep.dei.controller.RegisterAirControlAreaController;
import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterAirControlAreaUI {

    private final RegisterAirControlAreaController controller;
    private final Scanner scanner;

    public RegisterAirControlAreaUI(RegisterAirControlAreaController controller, Scanner scanner) {
        if (controller == null) {
            throw new IllegalArgumentException("Register air control area controller cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("=================================");
        System.out.println("     Register Air Control Area   ");
        System.out.println("=================================");

        try {
            String code = readText("Area code: ");
            String name = readText("Area name: ");

            int numberOfCoordinates = readInt("Number of boundary coordinates: ");

            List<RegisterAirControlAreaRequest.CoordinateRequest> coordinates =
                    readCoordinates(numberOfCoordinates);

            RegisterAirControlAreaRequest request = new RegisterAirControlAreaRequest(
                    code,
                    name,
                    coordinates
            );

            AirControlArea area = controller.registerAirControlArea(request);

            System.out.println();
            System.out.println("Air control area registered successfully.");
            System.out.println("Code: " + area.code());
            System.out.println("Name: " + area.name());
            System.out.println("Boundary points: " + area.geographicBoundary().size());

        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println("Could not register air control area.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private List<RegisterAirControlAreaRequest.CoordinateRequest> readCoordinates(int numberOfCoordinates) {
        if (numberOfCoordinates < 3) {
            throw new IllegalArgumentException("A geographic boundary must have at least three coordinates.");
        }

        List<RegisterAirControlAreaRequest.CoordinateRequest> coordinates = new ArrayList<>();

        for (int i = 1; i <= numberOfCoordinates; i++) {
            System.out.println();
            System.out.println("Coordinate " + i);

            double latitude = readDouble("Latitude: ");
            double longitude = readDouble("Longitude: ");

            coordinates.add(new RegisterAirControlAreaRequest.CoordinateRequest(latitude, longitude));
        }

        return coordinates;
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
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

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please insert a decimal number.");
            }
        }
    }
}