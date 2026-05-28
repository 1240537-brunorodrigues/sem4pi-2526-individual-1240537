package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.aircraft.model.AircraftModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryAircraftModelRepository implements AircraftModelRepository {

    private final HashMap<String, AircraftModel> aircraftModelsByKey = new HashMap<>();

    @Override
    public AircraftModel save(AircraftModel aircraftModel) {
        if (aircraftModel == null) {
            throw new IllegalArgumentException("Aircraft model cannot be null.");
        }

        aircraftModelsByKey.put(
                key(aircraftModel.modelName(), aircraftModel.manufacturer().name()),
                aircraftModel
        );

        return aircraftModel;
    }

    @Override
    public Optional<AircraftModel> findByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    ) {
        validateModelName(modelName);
        validateManufacturerName(manufacturerName);

        return Optional.ofNullable(aircraftModelsByKey.get(key(modelName, manufacturerName)));
    }

    @Override
    public boolean existsByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    ) {
        validateModelName(modelName);
        validateManufacturerName(manufacturerName);

        return aircraftModelsByKey.containsKey(key(modelName, manufacturerName));
    }

    @Override
    public List<AircraftModel> findAll() {
        return new ArrayList<>(aircraftModelsByKey.values());
    }

    private String key(String modelName, String manufacturerName) {
        return normalize(modelName) + "::" + normalize(manufacturerName);
    }

    private String normalize(String value) {
        return value.trim().toLowerCase();
    }

    private void validateModelName(String modelName) {
        if (modelName == null || modelName.isBlank()) {
            throw new IllegalArgumentException("Aircraft model name cannot be empty.");
        }
    }

    private void validateManufacturerName(String manufacturerName) {
        if (manufacturerName == null || manufacturerName.isBlank()) {
            throw new IllegalArgumentException("Aircraft manufacturer name cannot be empty.");
        }
    }
}