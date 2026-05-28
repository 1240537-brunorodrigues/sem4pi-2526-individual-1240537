package pt.ipp.isep.dei.domain.aircraft.model;

import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;

import java.time.LocalDate;
import java.util.Objects;

public class CertifiedEngineModel {

    private final AircraftEngineModel aircraftEngineModel;
    private final LocalDate certificationDate;
    private final String certificationStatus;

    public CertifiedEngineModel(
            AircraftEngineModel aircraftEngineModel,
            LocalDate certificationDate,
            String certificationStatus
    ) {
        if (aircraftEngineModel == null) {
            throw new IllegalArgumentException("Aircraft engine model cannot be null.");
        }

        if (certificationDate == null) {
            throw new IllegalArgumentException("Certification date cannot be null.");
        }

        if (certificationStatus == null || certificationStatus.isBlank()) {
            throw new IllegalArgumentException("Certification status cannot be empty.");
        }

        this.aircraftEngineModel = aircraftEngineModel;
        this.certificationDate = certificationDate;
        this.certificationStatus = certificationStatus.trim().toUpperCase().replace(" ", "_");
    }

    public AircraftEngineModel aircraftEngineModel() {
        return aircraftEngineModel;
    }

    public LocalDate certificationDate() {
        return certificationDate;
    }

    public String certificationStatus() {
        return certificationStatus;
    }

    public boolean isActive() {
        return "ACTIVE".equals(certificationStatus);
    }

    @Override
    public String toString() {
        return aircraftEngineModel.modelName() + " - " + certificationStatus;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof CertifiedEngineModel that)) {
            return false;
        }

        return aircraftEngineModel.equals(that.aircraftEngineModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftEngineModel);
    }
}