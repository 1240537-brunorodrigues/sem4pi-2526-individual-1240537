package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryAirControlAreaRepositoryTest {

    private AirControlArea createArea(String code) {
        return new AirControlArea(
                new AreaCode(code),
                "Lisbon FIR",
                new GeographicBoundary(List.of(
                        new Coordinate(41.0, -8.0),
                        new Coordinate(42.0, -8.0),
                        new Coordinate(42.0, -7.0)
                ))
        );
    }

    @Test
    void shouldSaveAirControlArea() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();
        AirControlArea area = createArea("LPPC");

        AirControlArea savedArea = repository.save(area);

        assertEquals(area, savedArea);
        assertTrue(repository.existsByCode(new AreaCode("LPPC")));
    }

    @Test
    void shouldFindAirControlAreaByCode() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();
        AirControlArea area = createArea("LPPC");

        repository.save(area);

        assertTrue(repository.findByCode(new AreaCode("LPPC")).isPresent());
        assertEquals(area, repository.findByCode(new AreaCode("lppc")).orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenAreaDoesNotExist() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();

        assertTrue(repository.findByCode(new AreaCode("LPPC")).isEmpty());
    }

    @Test
    void shouldReturnAllAreas() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();

        repository.save(createArea("LPPC"));
        repository.save(createArea("LAAA"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldReplaceAreaWithSameCode() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();

        repository.save(createArea("LPPC"));
        repository.save(createArea("lppc"));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldRejectNullArea() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    void shouldRejectNullCodeOnFind() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.findByCode(null));
    }

    @Test
    void shouldRejectNullCodeOnExists() {
        AirControlAreaRepository repository = new InMemoryAirControlAreaRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.existsByCode(null));
    }
}