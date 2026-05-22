package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.application.CreateAircraftEngineModelRequest;
import pt.ipp.isep.dei.controller.CreateAircraftEngineModelController;
import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;

import java.util.Scanner;

public class CreateAircraftEngineModelUI {

    private final CreateAircraftEngineModelController controller;
    private final Scanner scanner;

    public CreateAircraftEngineModelUI(
            CreateAircraftEngineModelController controller,
            Scanner scanner
    ) {
        if (controller == null) {
            throw new IllegalArgumentException("Create aircraft engine model controller cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("=================================");
        System.out.println("   Create Aircraft Engine Model  ");
        System.out.println("=================================");

        try {
            String modelName = readText("Engine model name: ");
            double power = readDouble("Power: ");
            double efficiency = readDouble("Efficiency: ");
            String manufacturerName = readText("Manufacturer name: ");
            String manufacturerCountry = readText("Manufacturer country: ");
            String engineType = readText("Engine type: ");
            String fuelType = readText("Fuel type: ");

            CreateAircraftEngineModelRequest request = new CreateAircraftEngineModelRequest(
                    modelName,
                    power,
                    efficiency,
                    manufacturerName,
                    manufacturerCountry,
                    engineType,
                    fuelType
            );

            AircraftEngineModel engineModel = controller.createAircraftEngineModel(request);

            System.out.println();
            System.out.println("Aircraft engine model created successfully.");
            System.out.println("Model name: " + engineModel.modelName());
            System.out.println("Power: " + engineModel.power());
            System.out.println("Efficiency: " + engineModel.efficiency());
            System.out.println("Manufacturer: " + engineModel.manufacturer());
            System.out.println("Engine type: " + engineModel.engineType());
            System.out.println("Fuel type: " + engineModel.fuelType());

        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println("Could not create aircraft engine model.");
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