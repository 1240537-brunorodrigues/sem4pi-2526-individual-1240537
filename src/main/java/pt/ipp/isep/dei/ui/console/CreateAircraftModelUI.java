package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.application.CreateAircraftModelRequest;
import pt.ipp.isep.dei.controller.CreateAircraftModelController;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftModel;

import java.util.Scanner;

public class CreateAircraftModelUI {

    private final CreateAircraftModelController controller;
    private final Scanner scanner;

    public CreateAircraftModelUI(
            CreateAircraftModelController controller,
            Scanner scanner
    ) {
        if (controller == null) {
            throw new IllegalArgumentException("Create aircraft model controller cannot be null.");
        }

        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }

        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("=================================");
        System.out.println("       Create Aircraft Model     ");
        System.out.println("=================================");

        try {
            String modelName = readText("Aircraft model name: ");
            double emptyWeight = readDouble("Empty weight: ");
            double maximumTakeOffWeight = readDouble("Maximum take-off weight: ");
            double maximumZeroFuelWeight = readDouble("Maximum zero fuel weight: ");
            double maximumFuelCapacity = readDouble("Maximum fuel capacity: ");
            double serviceCeiling = readDouble("Service ceiling: ");
            double cruiseSpeed = readDouble("Cruise speed: ");
            double wingArea = readDouble("Wing area: ");
            double dragCoefficient = readDouble("Drag coefficient: ");
            double liftCoefficient = readDouble("Lift coefficient: ");
            int capacity = readInt("Capacity: ");
            String manufacturerName = readText("Manufacturer name: ");
            String manufacturerCountry = readText("Manufacturer country: ");
            String aircraftType = readText("Aircraft type: ");

            CreateAircraftModelRequest request = new CreateAircraftModelRequest(
                    modelName,
                    emptyWeight,
                    maximumTakeOffWeight,
                    maximumZeroFuelWeight,
                    maximumFuelCapacity,
                    serviceCeiling,
                    cruiseSpeed,
                    wingArea,
                    dragCoefficient,
                    liftCoefficient,
                    capacity,
                    manufacturerName,
                    manufacturerCountry,
                    aircraftType
            );

            AircraftModel aircraftModel = controller.createAircraftModel(request);

            System.out.println();
            System.out.println("Aircraft model created successfully.");
            System.out.println("Model name: " + aircraftModel.modelName());
            System.out.println("Manufacturer: " + aircraftModel.manufacturer());
            System.out.println("Aircraft type: " + aircraftModel.aircraftType());
            System.out.println("Capacity: " + aircraftModel.capacity());
            System.out.println("Maximum take-off weight: " + aircraftModel.maximumTakeOffWeight());
            System.out.println("Cruise speed: " + aircraftModel.cruiseSpeed());

        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println("Could not create aircraft model.");
            System.out.println("Reason: " + exception.getMessage());
        }
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