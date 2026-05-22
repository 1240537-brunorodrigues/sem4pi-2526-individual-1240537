package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.aircraft.engine.AircraftEngineModel;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineManufacturer;
import pt.ipp.isep.dei.domain.aircraft.engine.EngineType;
import pt.ipp.isep.dei.domain.aircraft.engine.FuelType;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryAircraftEngineModelRepositoryTest {

    private AircraftEngineModel createEngineModel(String modelName, String manufacturerName) {
        return new AircraftEngineModel(
                modelName,
                331.0,
                0.92,
                new EngineManufacturer(manufacturerName, "United Kingdom"),
                new EngineType("TURBOFAN"),
                new FuelType("JET_A1")
        );
    }

    @Test
    void shouldSaveAircraftEngineModel() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();
        AircraftEngineModel engineModel = createEngineModel("Trent 1000", "Rolls-Royce");

        AircraftEngineModel savedEngineModel = repository.save(engineModel);

        assertEquals(engineModel, savedEngineModel);
        assertTrue(repository.existsByModelNameAndManufacturerName("Trent 1000", "Rolls-Royce"));
    }

    @Test
    void shouldFindAircraftEngineModelByModelNameAndManufacturerName() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();
        AircraftEngineModel engineModel = createEngineModel("Trent 1000", "Rolls-Royce");

        repository.save(engineModel);

        assertTrue(repository.findByModelNameAndManufacturerName("trent 1000", "rolls-royce").isPresent());
        assertEquals(
                engineModel,
                repository.findByModelNameAndManufacturerName("Trent 1000", "Rolls-Royce").orElseThrow()
        );
    }

    @Test
    void shouldReturnEmptyWhenEngineModelDoesNotExist() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertTrue(repository.findByModelNameAndManufacturerName("Trent 1000", "Rolls-Royce").isEmpty());
    }

    @Test
    void shouldReturnAllAircraftEngineModels() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        repository.save(createEngineModel("Trent 1000", "Rolls-Royce"));
        repository.save(createEngineModel("LEAP-1A", "CFM International"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldReplaceAircraftEngineModelWithSameModelNameAndManufacturerName() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        repository.save(createEngineModel("Trent 1000", "Rolls-Royce"));
        repository.save(createEngineModel("trent 1000", "rolls-royce"));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldRejectNullAircraftEngineModel() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    void shouldRejectNullModelNameOnFind() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName(null, "Rolls-Royce"));
    }

    @Test
    void shouldRejectBlankModelNameOnFind() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName("   ", "Rolls-Royce"));
    }

    @Test
    void shouldRejectNullManufacturerNameOnFind() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName("Trent 1000", null));
    }

    @Test
    void shouldRejectBlankManufacturerNameOnFind() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName("Trent 1000", "   "));
    }

    @Test
    void shouldRejectNullModelNameOnExists() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.existsByModelNameAndManufacturerName(null, "Rolls-Royce"));
    }

    @Test
    void shouldRejectNullManufacturerNameOnExists() {
        AircraftEngineModelRepository repository = new InMemoryAircraftEngineModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.existsByModelNameAndManufacturerName("Trent 1000", null));
    }
}