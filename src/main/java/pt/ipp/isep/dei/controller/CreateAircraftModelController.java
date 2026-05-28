package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.CreateAircraftModelRequest;
import pt.ipp.isep.dei.application.CreateAircraftModelService;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftModel;

public class CreateAircraftModelController {

    private final CreateAircraftModelService createAircraftModelService;

    public CreateAircraftModelController(CreateAircraftModelService createAircraftModelService) {
        if (createAircraftModelService == null) {
            throw new IllegalArgumentException("Create aircraft model service cannot be null.");
        }

        this.createAircraftModelService = createAircraftModelService;
    }

    public AircraftModel createAircraftModel(CreateAircraftModelRequest request) {
        return createAircraftModelService.createAircraftModel(request);
    }
}