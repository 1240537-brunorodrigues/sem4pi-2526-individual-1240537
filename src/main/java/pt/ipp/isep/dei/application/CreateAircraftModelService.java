package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.aircraft.model.AircraftManufacturer;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftModel;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftType;
import pt.ipp.isep.dei.repository.AircraftModelRepository;

public class CreateAircraftModelService {

    private final AircraftModelRepository aircraftModelRepository;
    private final AuthorizationService authorizationService;

    public CreateAircraftModelService(
            AircraftModelRepository aircraftModelRepository,
            AuthorizationService authorizationService
    ) {
        if (aircraftModelRepository == null) {
            throw new IllegalArgumentException("Aircraft model repository cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        this.aircraftModelRepository = aircraftModelRepository;
        this.authorizationService = authorizationService;
    }

    public AircraftModel createAircraftModel(CreateAircraftModelRequest request) {
        authorizationService.requirePermission("CREATE_AIRCRAFT_MODEL");

        if (request == null) {
            throw new IllegalArgumentException("Create aircraft model request cannot be null.");
        }

        if (aircraftModelRepository.existsByModelNameAndManufacturerName(
                request.modelName(),
                request.manufacturerName()
        )) {
            throw new IllegalArgumentException("An aircraft model with this model name and manufacturer already exists.");
        }

        AircraftModel aircraftModel = new AircraftModel(
                request.modelName(),
                request.emptyWeight(),
                request.maximumTakeOffWeight(),
                request.maximumZeroFuelWeight(),
                request.maximumFuelCapacity(),
                request.serviceCeiling(),
                request.cruiseSpeed(),
                request.wingArea(),
                request.dragCoefficient(),
                request.liftCoefficient(),
                request.capacity(),
                new AircraftManufacturer(
                        request.manufacturerName(),
                        request.manufacturerCountry()
                ),
                new AircraftType(request.aircraftType())
        );

        return aircraftModelRepository.save(aircraftModel);
    }
}