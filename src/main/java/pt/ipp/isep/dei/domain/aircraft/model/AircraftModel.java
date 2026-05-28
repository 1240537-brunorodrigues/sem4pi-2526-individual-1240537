package pt.ipp.isep.dei.domain.aircraft.model;

import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AircraftModel {

    private final String modelName;
    private final double emptyWeight;
    private final double maximumTakeOffWeight;
    private final double maximumZeroFuelWeight;
    private final double maximumFuelCapacity;
    private final double serviceCeiling;
    private final double cruiseSpeed;
    private final double wingArea;
    private final double dragCoefficient;
    private final double liftCoefficient;
    private final int capacity;
    private final AircraftManufacturer manufacturer;
    private final AircraftType aircraftType;
    private final Set<CertifiedEngineModel> certifiedEngineModels = new HashSet<>();

    public AircraftModel(
            String modelName,
            double emptyWeight,
            double maximumTakeOffWeight,
            double maximumZeroFuelWeight,
            double maximumFuelCapacity,
            double serviceCeiling,
            double cruiseSpeed,
            double wingArea,
            double dragCoefficient,
            double liftCoefficient,
            int capacity,
            AircraftManufacturer manufacturer,
            AircraftType aircraftType
    ) {
        if (modelName == null || modelName.isBlank()) {
            throw new IllegalArgumentException("Aircraft model name cannot be empty.");
        }

        if (emptyWeight <= 0) {
            throw new IllegalArgumentException("Empty weight must be positive.");
        }

        if (maximumTakeOffWeight <= 0) {
            throw new IllegalArgumentException("Maximum take-off weight must be positive.");
        }

        if (maximumZeroFuelWeight <= 0) {
            throw new IllegalArgumentException("Maximum zero fuel weight must be positive.");
        }

        if (maximumFuelCapacity <= 0) {
            throw new IllegalArgumentException("Maximum fuel capacity must be positive.");
        }

        if (serviceCeiling <= 0) {
            throw new IllegalArgumentException("Service ceiling must be positive.");
        }

        if (cruiseSpeed <= 0) {
            throw new IllegalArgumentException("Cruise speed must be positive.");
        }

        if (wingArea <= 0) {
            throw new IllegalArgumentException("Wing area must be positive.");
        }

        if (dragCoefficient <= 0) {
            throw new IllegalArgumentException("Drag coefficient must be positive.");
        }

        if (liftCoefficient <= 0) {
            throw new IllegalArgumentException("Lift coefficient must be positive.");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        if (emptyWeight >= maximumZeroFuelWeight) {
            throw new IllegalArgumentException("Empty weight must be lower than maximum zero fuel weight.");
        }

        if (maximumZeroFuelWeight >= maximumTakeOffWeight) {
            throw new IllegalArgumentException("Maximum zero fuel weight must be lower than maximum take-off weight.");
        }

        if (manufacturer == null) {
            throw new IllegalArgumentException("Aircraft manufacturer cannot be null.");
        }

        if (aircraftType == null) {
            throw new IllegalArgumentException("Aircraft type cannot be null.");
        }

        this.modelName = modelName.trim();
        this.emptyWeight = emptyWeight;
        this.maximumTakeOffWeight = maximumTakeOffWeight;
        this.maximumZeroFuelWeight = maximumZeroFuelWeight;
        this.maximumFuelCapacity = maximumFuelCapacity;
        this.serviceCeiling = serviceCeiling;
        this.cruiseSpeed = cruiseSpeed;
        this.wingArea = wingArea;
        this.dragCoefficient = dragCoefficient;
        this.liftCoefficient = liftCoefficient;
        this.capacity = capacity;
        this.manufacturer = manufacturer;
        this.aircraftType = aircraftType;
    }

    public String modelName() {
        return modelName;
    }

    public double emptyWeight() {
        return emptyWeight;
    }

    public double maximumTakeOffWeight() {
        return maximumTakeOffWeight;
    }

    public double maximumZeroFuelWeight() {
        return maximumZeroFuelWeight;
    }

    public double maximumFuelCapacity() {
        return maximumFuelCapacity;
    }

    public double serviceCeiling() {
        return serviceCeiling;
    }

    public double cruiseSpeed() {
        return cruiseSpeed;
    }

    public double wingArea() {
        return wingArea;
    }

    public double dragCoefficient() {
        return dragCoefficient;
    }

    public double liftCoefficient() {
        return liftCoefficient;
    }

    public int capacity() {
        return capacity;
    }

    public AircraftManufacturer manufacturer() {
        return manufacturer;
    }

    public AircraftType aircraftType() {
        return aircraftType;
    }

    public Set<CertifiedEngineModel> certifiedEngineModels() {
        return Collections.unmodifiableSet(certifiedEngineModels);
    }

    public void addCertifiedEngineModel(CertifiedEngineModel certifiedEngineModel) {
        if (certifiedEngineModel == null) {
            throw new IllegalArgumentException("Certified engine model cannot be null.");
        }

        certifiedEngineModels.add(certifiedEngineModel);
    }

    public void removeCertifiedEngineModel(AircraftEngineModel aircraftEngineModel) {
        if (aircraftEngineModel == null) {
            throw new IllegalArgumentException("Aircraft engine model cannot be null.");
        }

        certifiedEngineModels.removeIf(
                certifiedEngineModel -> certifiedEngineModel.aircraftEngineModel().equals(aircraftEngineModel)
        );
    }

    public boolean isEngineModelCertified(AircraftEngineModel aircraftEngineModel) {
        if (aircraftEngineModel == null) {
            return false;
        }

        return certifiedEngineModels.stream()
                .anyMatch(certifiedEngineModel ->
                        certifiedEngineModel.aircraftEngineModel().equals(aircraftEngineModel)
                                && certifiedEngineModel.isActive()
                );
    }

    @Override
    public String toString() {
        return "AircraftModel{" +
                "modelName='" + modelName + '\'' +
                ", manufacturer=" + manufacturer +
                ", aircraftType=" + aircraftType +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AircraftModel that)) {
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