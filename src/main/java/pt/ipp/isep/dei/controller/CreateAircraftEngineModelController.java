package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.CreateAircraftEngineModelRequest;
import pt.ipp.isep.dei.application.CreateAircraftEngineModelService;
import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;

public class CreateAircraftEngineModelController {

    private final CreateAircraftEngineModelService createAircraftEngineModelService;

    public CreateAircraftEngineModelController(CreateAircraftEngineModelService createAircraftEngineModelService) {
        if (createAircraftEngineModelService == null) {
            throw new IllegalArgumentException("Create aircraft engine model service cannot be null.");
        }

        this.createAircraftEngineModelService = createAircraftEngineModelService;
    }

    public AircraftEngineModel createAircraftEngineModel(CreateAircraftEngineModelRequest request) {
        return createAircraftEngineModelService.createAircraftEngineModel(request);
    }
}