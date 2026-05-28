package pt.ipp.isep.dei.domain.aircraft.model;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineManufacturer;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineType;
import pt.ipp.isep.dei.domain.aircraft.engine.FuelType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AircraftModelTest {

    private AircraftManufacturer manufacturer() {
        return new AircraftManufacturer("Boeing", "United States");
    }

    private AircraftType aircraftType() {
        return new AircraftType("WIDE_BODY");
    }

    private AircraftEngineModel engineModel() {
        return new AircraftEngineModel(
                "GE90",
                512.0,
                0.90,
                new EngineManufacturer("General Electric", "United States"),
                new EngineType("TURBOFAN"),
                new FuelType("JET_A1")
        );
    }

    private AircraftModel validAircraftModel() {
        return new AircraftModel(
                "777-300ER",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturer(),
                aircraftType()
        );
    }

    @Test
    void shouldCreateValidAircraftModel() {
        AircraftModel aircraftModel = validAircraftModel();

        assertEquals("777-300ER", aircraftModel.modelName());
        assertEquals(167829, aircraftModel.emptyWeight());
        assertEquals(351534, aircraftModel.maximumTakeOffWeight());
        assertEquals(237682, aircraftModel.maximumZeroFuelWeight());
        assertEquals(181280, aircraftModel.maximumFuelCapacity());
        assertEquals(13140, aircraftModel.serviceCeiling());
        assertEquals(905, aircraftModel.cruiseSpeed());
        assertEquals(436.8, aircraftModel.wingArea());
        assertEquals(0.021, aircraftModel.dragCoefficient());
        assertEquals(1.5, aircraftModel.liftCoefficient());
        assertEquals(396, aircraftModel.capacity());
        assertEquals(manufacturer(), aircraftModel.manufacturer());
        assertEquals(aircraftType(), aircraftModel.aircraftType());
    }

    @Test
    void shouldTrimModelName() {
        AircraftModel aircraftModel = new AircraftModel(
                "  777-300ER  ",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturer(),
                aircraftType()
        );

        assertEquals("777-300ER", aircraftModel.modelName());
    }

    @Test
    void shouldRejectInvalidWeightHierarchyWhenEmptyWeightIsTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel(
                "777-300ER",
                250000,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturer(),
                aircraftType()
        ));
    }

    @Test
    void shouldRejectInvalidWeightHierarchyWhenZeroFuelWeightIsTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel(
                "777-300ER",
                167829,
                351534,
                400000,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturer(),
                aircraftType()
        ));
    }

    @Test
    void shouldRejectBlankModelName() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel(
                "   ",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturer(),
                aircraftType()
        ));
    }

    @Test
    void shouldRejectNonPositiveValues() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel(
                "777-300ER",
                0,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturer(),
                aircraftType()
        ));
    }

    @Test
    void shouldRejectNullManufacturer() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel(
                "777-300ER",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                null,
                aircraftType()
        ));
    }

    @Test
    void shouldRejectNullAircraftType() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftModel(
                "777-300ER",
                167829,
                351534,
                237682,
                181280,
                13140,
                905,
                436.8,
                0.021,
                1.5,
                396,
                manufacturer(),
                null
        ));
    }

    @Test
    void shouldAddCertifiedEngineModel() {
        AircraftModel aircraftModel = validAircraftModel();

        CertifiedEngineModel certifiedEngineModel = new CertifiedEngineModel(
                engineModel(),
                LocalDate.of(2026, 1, 1),
                "ACTIVE"
        );

        aircraftModel.addCertifiedEngineModel(certifiedEngineModel);

        assertEquals(1, aircraftModel.certifiedEngineModels().size());
        assertTrue(aircraftModel.isEngineModelCertified(engineModel()));
    }

    @Test
    void shouldRejectNullCertifiedEngineModel() {
        AircraftModel aircraftModel = validAircraftModel();

        assertThrows(IllegalArgumentException.class,
                () -> aircraftModel.addCertifiedEngineModel(null));
    }

    @Test
    void shouldRemoveCertifiedEngineModel() {
        AircraftModel aircraftModel = validAircraftModel();

        CertifiedEngineModel certifiedEngineModel = new CertifiedEngineModel(
                engineModel(),
                LocalDate.of(2026, 1, 1),
                "ACTIVE"
        );

        aircraftModel.addCertifiedEngineModel(certifiedEngineModel);
        aircraftModel.removeCertifiedEngineModel(engineModel());

        assertTrue(aircraftModel.certifiedEngineModels().isEmpty());
        assertFalse(aircraftModel.isEngineModelCertified(engineModel()));
    }

    @Test
    void shouldRejectNullEngineModelWhenRemovingCertification() {
        AircraftModel aircraftModel = validAircraftModel();

        assertThrows(IllegalArgumentException.class,
                () -> aircraftModel.removeCertifiedEngineModel(null));
    }

    @Test
    void certifiedEngineModelsCollectionShouldBeImmutable() {
        AircraftModel aircraftModel = validAircraftModel();

        assertThrows(UnsupportedOperationException.class,
                () -> aircraftModel.certifiedEngineModels().add(
                        new CertifiedEngineModel(engineModel(), LocalDate.now(), "ACTIVE")
                ));
    }

    @Test
    void aircraftModelsWithSameNameAndManufacturerShouldBeEqual() {
        AircraftModel aircraftModel1 = validAircraftModel();

        AircraftModel aircraftModel2 = new AircraftModel(
                "777-300er",
                160000,
                340000,
                230000,
                180000,
                12000,
                880,
                430,
                0.022,
                1.4,
                350,
                manufacturer(),
                new AircraftType("WIDE_BODY")
        );

        assertEquals(aircraftModel1, aircraftModel2);
        assertEquals(aircraftModel1.hashCode(), aircraftModel2.hashCode());
    }
}