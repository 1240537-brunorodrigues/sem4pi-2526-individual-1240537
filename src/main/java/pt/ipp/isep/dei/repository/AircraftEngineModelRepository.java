package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;

import java.util.List;
import java.util.Optional;

public interface AircraftEngineModelRepository {

    AircraftEngineModel save(AircraftEngineModel aircraftEngineModel);

    Optional<AircraftEngineModel> findByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    );

    boolean existsByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    );

    List<AircraftEngineModel> findAll();
}