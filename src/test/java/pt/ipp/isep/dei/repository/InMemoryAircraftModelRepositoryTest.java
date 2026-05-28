package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftManufacturer;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftModel;
import pt.ipp.isep.dei.domain.aircraft.model.AircraftType;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryAircraftModelRepositoryTest {

    private AircraftModel createAircraftModel(String modelName, String manufacturerName) {
        return new AircraftModel(
                modelName,
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
                new AircraftManufacturer(manufacturerName, "United States"),
                new AircraftType("WIDE_BODY")
        );
    }

    @Test
    void shouldSaveAircraftModel() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();
        AircraftModel aircraftModel = createAircraftModel("777-300ER", "Boeing");

        AircraftModel savedAircraftModel = repository.save(aircraftModel);

        assertEquals(aircraftModel, savedAircraftModel);
        assertTrue(repository.existsByModelNameAndManufacturerName("777-300ER", "Boeing"));
    }

    @Test
    void shouldFindAircraftModelByModelNameAndManufacturerName() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();
        AircraftModel aircraftModel = createAircraftModel("777-300ER", "Boeing");

        repository.save(aircraftModel);

        assertTrue(repository.findByModelNameAndManufacturerName("777-300er", "boeing").isPresent());
        assertEquals(
                aircraftModel,
                repository.findByModelNameAndManufacturerName("777-300ER", "Boeing").orElseThrow()
        );
    }

    @Test
    void shouldReturnEmptyWhenAircraftModelDoesNotExist() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertTrue(repository.findByModelNameAndManufacturerName("777-300ER", "Boeing").isEmpty());
    }

    @Test
    void shouldReturnAllAircraftModels() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        repository.save(createAircraftModel("777-300ER", "Boeing"));
        repository.save(createAircraftModel("A350-900", "Airbus"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldReplaceAircraftModelWithSameModelNameAndManufacturerName() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        repository.save(createAircraftModel("777-300ER", "Boeing"));
        repository.save(createAircraftModel("777-300er", "boeing"));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldRejectNullAircraftModel() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    void shouldRejectNullModelNameOnFind() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName(null, "Boeing"));
    }

    @Test
    void shouldRejectBlankModelNameOnFind() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName("   ", "Boeing"));
    }

    @Test
    void shouldRejectNullManufacturerNameOnFind() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName("777-300ER", null));
    }

    @Test
    void shouldRejectBlankManufacturerNameOnFind() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.findByModelNameAndManufacturerName("777-300ER", "   "));
    }

    @Test
    void shouldRejectNullModelNameOnExists() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.existsByModelNameAndManufacturerName(null, "Boeing"));
    }

    @Test
    void shouldRejectNullManufacturerNameOnExists() {
        AircraftModelRepository repository = new InMemoryAircraftModelRepository();

        assertThrows(IllegalArgumentException.class,
                () -> repository.existsByModelNameAndManufacturerName("777-300ER", null));
    }
}