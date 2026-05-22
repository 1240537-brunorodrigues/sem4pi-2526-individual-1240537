package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryAircraftEngineModelRepository implements AircraftEngineModelRepository {

    private final HashMap<String, AircraftEngineModel> engineModelsByKey = new HashMap<>();

    @Override
    public AircraftEngineModel save(AircraftEngineModel aircraftEngineModel) {
        if (aircraftEngineModel == null) {
            throw new IllegalArgumentException("Aircraft engine model cannot be null.");
        }

        engineModelsByKey.put(
                key(aircraftEngineModel.modelName(), aircraftEngineModel.manufacturer().name()),
                aircraftEngineModel
        );

        return aircraftEngineModel;
    }

    @Override
    public Optional<AircraftEngineModel> findByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    ) {
        validateModelName(modelName);
        validateManufacturerName(manufacturerName);

        return Optional.ofNullable(engineModelsByKey.get(key(modelName, manufacturerName)));
    }

    @Override
    public boolean existsByModelNameAndManufacturerName(
            String modelName,
            String manufacturerName
    ) {
        validateModelName(modelName);
        validateManufacturerName(manufacturerName);

        return engineModelsByKey.containsKey(key(modelName, manufacturerName));
    }

    @Override
    public List<AircraftEngineModel> findAll() {
        return new ArrayList<>(engineModelsByKey.values());
    }

    private String key(String modelName, String manufacturerName) {
        return normalize(modelName) + "::" + normalize(manufacturerName);
    }

    private String normalize(String value) {
        return value.trim().toLowerCase();
    }

    private void validateModelName(String modelName) {
        if (modelName == null || modelName.isBlank()) {
            throw new IllegalArgumentException("Aircraft engine model name cannot be empty.");
        }
    }

    private void validateManufacturerName(String manufacturerName) {
        if (manufacturerName == null || manufacturerName.isBlank()) {
            throw new IllegalArgumentException("Engine manufacturer name cannot be empty.");
        }
    }
}