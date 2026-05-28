package pt.ipp.isep.dei.domain.aircraft.model;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineManufacturer;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineType;
import pt.ipp.isep.dei.domain.aircraft.engine.FuelType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CertifiedEngineModelTest {

    private AircraftEngineModel engineModel() {
        return new AircraftEngineModel(
                "Trent 1000",
                331.0,
                0.92,
                new EngineManufacturer("Rolls-Royce", "United Kingdom"),
                new EngineType("TURBOFAN"),
                new FuelType("JET_A1")
        );
    }

    @Test
    void shouldCreateValidCertifiedEngineModel() {
        CertifiedEngineModel certifiedEngineModel = new CertifiedEngineModel(
                engineModel(),
                LocalDate.of(2026, 1, 1),
                "ACTIVE"
        );

        assertEquals(engineModel(), certifiedEngineModel.aircraftEngineModel());
        assertEquals(LocalDate.of(2026, 1, 1), certifiedEngineModel.certificationDate());
        assertEquals("ACTIVE", certifiedEngineModel.certificationStatus());
        assertTrue(certifiedEngineModel.isActive());
    }

    @Test
    void shouldNormalizeCertificationStatus() {
        CertifiedEngineModel certifiedEngineModel = new CertifiedEngineModel(
                engineModel(),
                LocalDate.of(2026, 1, 1),
                "under review"
        );

        assertEquals("UNDER_REVIEW", certifiedEngineModel.certificationStatus());
        assertFalse(certifiedEngineModel.isActive());
    }

    @Test
    void shouldRejectNullEngineModel() {
        assertThrows(IllegalArgumentException.class,
                () -> new CertifiedEngineModel(null, LocalDate.of(2026, 1, 1), "ACTIVE"));
    }

    @Test
    void shouldRejectNullCertificationDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new CertifiedEngineModel(engineModel(), null, "ACTIVE"));
    }

    @Test
    void shouldRejectBlankCertificationStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new CertifiedEngineModel(engineModel(), LocalDate.of(2026, 1, 1), "   "));
    }

    @Test
    void certifiedEngineModelsWithSameEngineModelShouldBeEqual() {
        CertifiedEngineModel certifiedEngineModel1 = new CertifiedEngineModel(
                engineModel(),
                LocalDate.of(2026, 1, 1),
                "ACTIVE"
        );

        CertifiedEngineModel certifiedEngineModel2 = new CertifiedEngineModel(
                engineModel(),
                LocalDate.of(2027, 1, 1),
                "UNDER_REVIEW"
        );

        assertEquals(certifiedEngineModel1, certifiedEngineModel2);
        assertEquals(certifiedEngineModel1.hashCode(), certifiedEngineModel2.hashCode());
    }
}