package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.aircraft.model.AircraftModel;

import java.util.List;
import java.util.Optional;

public interface AircraftModelRepository {

    AircraftModel save(AircraftModel aircraftModel);

    Optional<AircraftModel> findByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    );

    boolean existsByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    );

    List<AircraftModel> findAll();
}