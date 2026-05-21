package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.application.CreateAirportRequest;
import pt.ipp.isep.dei.controller.CreateAirportController;
import pt.ipp.isep.dei.domain.airport.Airport;

import java.util.Scanner;

public class CreateAirportUI {

    private final CreateAirportController controller;
    private final Scanner scanner;

    public CreateAirportUI(CreateAirportController controller, Scanner scanner) {
        if (controller == null) {
            throw new IllegalArgumentException("Create airport controller cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("=================================");
        System.out.println("          Create Airport         ");
        System.out.println("=================================");

        try {
            String name = readText("Airport name: ");
            String town = readText("Town: ");
            String countryName = readText("Country name: ");
            String countryCode = readText("Country code: ");
            double elevation = readDouble("Elevation: ");
            String iataCode = readText("IATA code: ");
            String icaoCode = readText("ICAO code: ");
            double latitude = readDouble("Latitude: ");
            double longitude = readDouble("Longitude: ");
            String airControlAreaCode = readText("Air control area code: ");

            CreateAirportRequest request = new CreateAirportRequest(
                    name,
                    town,
                    countryName,
                    countryCode,
                    elevation,
                    iataCode,
                    icaoCode,
                    latitude,
                    longitude,
                    airControlAreaCode
            );

            Airport airport = controller.createAirport(request);

            System.out.println();
            System.out.println("Airport created successfully.");
            System.out.println("Name: " + airport.name());
            System.out.println("Town: " + airport.town());
            System.out.println("Country: " + airport.country());
            System.out.println("IATA: " + airport.iataCode());
            System.out.println("ICAO: " + airport.icaoCode());
            System.out.println("Air control area: " + airport.airControlArea().code());

        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println("Could not create airport.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
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