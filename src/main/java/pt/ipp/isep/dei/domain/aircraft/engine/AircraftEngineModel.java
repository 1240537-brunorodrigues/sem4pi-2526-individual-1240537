package pt.ipp.isep.dei.domain.aircraft.engine;

import java.util.Objects;

public class AircraftEngineModel {

    private final String modelName;
    private final double power;
    private final double efficiency;
    private final EngineManufacturer manufacturer;
    private final EngineType engineType;
    private final FuelType fuelType;

    public AircraftEngineModel(
            String modelName,
            double power,
            double efficiency,
            EngineManufacturer manufacturer,
            EngineType engineType,
            FuelType fuelType
    ) {
        if (modelName == null || modelName.isBlank()) {
            throw new IllegalArgumentException("Aircraft engine model name cannot be empty.");
        }

        if (power <= 0) {
            throw new IllegalArgumentException("Aircraft engine power must be positive.");
        }

        if (efficiency <= 0) {
            throw new IllegalArgumentException("Aircraft engine efficiency must be positive.");
        }

        if (manufacturer == null) {
            throw new IllegalArgumentException("Engine manufacturer cannot be null.");
        }

        if (engineType == null) {
            throw new IllegalArgumentException("Engine type cannot be null.");
        }

        if (fuelType == null) {
            throw new IllegalArgumentException("Fuel type cannot be null.");
        }

        this.modelName = modelName.trim();
        this.power = power;
        this.efficiency = efficiency;
        this.manufacturer = manufacturer;
        this.engineType = engineType;
        this.fuelType = fuelType;
    }

    public String modelName() {
        return modelName;
    }

    public double power() {
        return power;
    }

    public double efficiency() {
        return efficiency;
    }

    public EngineManufacturer manufacturer() {
        return manufacturer;
    }

    public EngineType engineType() {
        return engineType;
    }

    public FuelType fuelType() {
        return fuelType;
    }

    @Override
    public String toString() {
        return "AircraftEngineModel{" +
                "modelName='" + modelName + '\'' +
                ", power=" + power +
                ", efficiency=" + efficiency +
                ", manufacturer=" + manufacturer +
                ", engineType=" + engineType +
                ", fuelType=" + fuelType +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AircraftEngineModel that)) {
            return false;
        }

        return modelName.equalsIgnoreCase(that.modelName)
                && manufacturer.equals(that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName.toLowerCase(), manufacturer);
    }
}