package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineManufacturer;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineType;
import pt.ipp.isep.dei.domain.aircraft.engine.FuelType;
import pt.ipp.isep.dei.repository.AircraftEngineModelRepository;

public class CreateAircraftEngineModelService {

    private final AircraftEngineModelRepository aircraftEngineModelRepository;
    private final AuthorizationService authorizationService;

    public CreateAircraftEngineModelService(
            AircraftEngineModelRepository aircraftEngineModelRepository,
            AuthorizationService authorizationService
    ) {
        if (aircraftEngineModelRepository == null) {
            throw new IllegalArgumentException("Aircraft engine model repository cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        this.aircraftEngineModelRepository = aircraftEngineModelRepository;
        this.authorizationService = authorizationService;
    }

    public AircraftEngineModel createAircraftEngineModel(CreateAircraftEngineModelRequest request) {
        authorizationService.requirePermission("CREATE_AIRCRAFT_ENGINE_MODEL");

        if (request == null) {
            throw new IllegalArgumentException("Create aircraft engine model request cannot be null.");
        }

        if (aircraftEngineModelRepository.existsByModelNameAndManufacturerName(
                request.modelName(),
                request.manufacturerName()
        )) {
            throw new IllegalArgumentException("An aircraft engine model with this model name and manufacturer already exists.");
        }

        AircraftEngineModel aircraftEngineModel = new AircraftEngineModel(
                request.modelName(),
                request.power(),
                request.efficiency(),
                new EngineManufacturer(
                        request.manufacturerName(),
                        request.manufacturerCountry()
                ),
                new EngineType(request.engineType()),
                new FuelType(request.fuelType())
        );

        return aircraftEngineModelRepository.save(aircraftEngineModel);
    }
}