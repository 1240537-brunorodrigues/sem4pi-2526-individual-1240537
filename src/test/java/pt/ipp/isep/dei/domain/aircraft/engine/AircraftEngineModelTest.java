package pt.ipp.isep.dei.domain.aircraft.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftEngineModelTest {

    private EngineManufacturer manufacturer() {
        return new EngineManufacturer("Rolls-Royce", "United Kingdom");
    }

    private EngineType engineType() {
        return new EngineType("TURBOFAN");
    }

    private FuelType fuelType() {
        return new FuelType("JET_A1");
    }

    @Test
    void shouldCreateValidAircraftEngineModel() {
        AircraftEngineModel engineModel = new AircraftEngineModel(
                "Trent 1000",
                331.0,
                0.92,
                manufacturer(),
                engineType(),
                fuelType()
        );

        assertEquals("Trent 1000", engineModel.modelName());
        assertEquals(331.0, engineModel.power());
        assertEquals(0.92, engineModel.efficiency());
        assertEquals(manufacturer(), engineModel.manufacturer());
        assertEquals(engineType(), engineModel.engineType());
        assertEquals(fuelType(), engineModel.fuelType());
    }

    @Test
    void shouldTrimModelName() {
        AircraftEngineModel engineModel = new AircraftEngineModel(
                "  Trent 1000  ",
                331.0,
                0.92,
                manufacturer(),
                engineType(),
                fuelType()
        );

        assertEquals("Trent 1000", engineModel.modelName());
    }

    @Test
    void shouldRejectNullModelName() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                null,
                331.0,
                0.92,
                manufacturer(),
                engineType(),
                fuelType()
        ));
    }

    @Test
    void shouldRejectBlankModelName() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "   ",
                331.0,
                0.92,
                manufacturer(),
                engineType(),
                fuelType()
        ));
    }

    @Test
    void shouldRejectZeroPower() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "Trent 1000",
                0,
                0.92,
                manufacturer(),
                engineType(),
                fuelType()
        ));
    }

    @Test
    void shouldRejectNegativePower() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "Trent 1000",
                -1,
                0.92,
                manufacturer(),
                engineType(),
                fuelType()
        ));
    }

    @Test
    void shouldRejectZeroEfficiency() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "Trent 1000",
                331.0,
                0,
                manufacturer(),
                engineType(),
                fuelType()
        ));
    }

    @Test
    void shouldRejectNegativeEfficiency() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "Trent 1000",
                331.0,
                -0.1,
                manufacturer(),
                engineType(),
                fuelType()
        ));
    }

    @Test
    void shouldRejectNullManufacturer() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "Trent 1000",
                331.0,
                0.92,
                null,
                engineType(),
                fuelType()
        ));
    }

    @Test
    void shouldRejectNullEngineType() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "Trent 1000",
                331.0,
                0.92,
                manufacturer(),
                null,
                fuelType()
        ));
    }

    @Test
    void shouldRejectNullFuelType() {
        assertThrows(IllegalArgumentException.class, () -> new AircraftEngineModel(
                "Trent 1000",
                331.0,
                0.92,
                manufacturer(),
                engineType(),
                null
        ));
    }

    @Test
    void engineModelsWithSameNameAndManufacturerShouldBeEqual() {
        AircraftEngineModel engineModel1 = new AircraftEngineModel(
                "Trent 1000",
                331.0,
                0.92,
                manufacturer(),
                engineType(),
                fuelType()
        );

        AircraftEngineModel engineModel2 = new AircraftEngineModel(
                "trent 1000",
                400.0,
                0.88,
                manufacturer(),
                new EngineType("TURBOFAN"),
                new FuelType("SAF")
        );

        assertEquals(engineModel1, engineModel2);
        assertEquals(engineModel1.hashCode(), engineModel2.hashCode());
    }
}