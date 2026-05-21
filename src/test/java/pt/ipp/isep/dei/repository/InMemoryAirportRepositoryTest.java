package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.airport.Airport;
import pt.ipp.isep.dei.domain.airport.IATACode;
import pt.ipp.isep.dei.domain.airport.ICAOCode;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.Country;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryAirportRepositoryTest {

    private AirControlArea validArea() {
        return new AirControlArea(
                new AreaCode("LPPC"),
                "Lisbon FIR",
                new GeographicBoundary(List.of(
                        new Coordinate(41.0, -8.0),
                        new Coordinate(42.0, -8.0),
                        new Coordinate(42.0, -7.0)
                ))
        );
    }

    private Airport createAirport(String iataCode, String icaoCode) {
        return new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                new Country("Portugal", "PT"),
                69,
                new IATACode(iataCode),
                new ICAOCode(icaoCode),
                new Coordinate(41.2481, -8.6814),
                validArea()
        );
    }

    @Test
    void shouldSaveAirport() {
        AirportRepository repository = new InMemoryAirportRepository();
        Airport airport = createAirport("OPO", "LPPR");

        Airport savedAirport = repository.save(airport);

        assertEquals(airport, savedAirport);
        assertTrue(repository.existsByIATACode(new IATACode("OPO")));
        assertTrue(repository.existsByICAOCode(new ICAOCode("LPPR")));
    }

    @Test
    void shouldFindAirportByIATACode() {
        AirportRepository repository = new InMemoryAirportRepository();
        Airport airport = createAirport("OPO", "LPPR");

        repository.save(airport);

        assertTrue(repository.findByIATACode(new IATACode("opo")).isPresent());
        assertEquals(airport, repository.findByIATACode(new IATACode("OPO")).orElseThrow());
    }

    @Test
    void shouldFindAirportByICAOCode() {
        AirportRepository repository = new InMemoryAirportRepository();
        Airport airport = createAirport("OPO", "LPPR");

        repository.save(airport);

        assertTrue(repository.findByICAOCode(new ICAOCode("lppr")).isPresent());
        assertEquals(airport, repository.findByICAOCode(new ICAOCode("LPPR")).orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenAirportDoesNotExistByIATACode() {
        AirportRepository repository = new InMemoryAirportRepository();

        assertTrue(repository.findByIATACode(new IATACode("OPO")).isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenAirportDoesNotExistByICAOCode() {
        AirportRepository repository = new InMemoryAirportRepository();

        assertTrue(repository.findByICAOCode(new ICAOCode("LPPR")).isEmpty());
    }

    @Test
    void shouldReturnAllAirports() {
        AirportRepository repository = new InMemoryAirportRepository();

        repository.save(createAirport("OPO", "LPPR"));
        repository.save(createAirport("LIS", "LPPT"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldReplaceAirportWithSameIATACode() {
        AirportRepository repository = new InMemoryAirportRepository();

        repository.save(createAirport("OPO", "LPPR"));
        repository.save(createAirport("opo", "LPPP"));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldRejectNullAirport() {
        AirportRepository repository = new InMemoryAirportRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    void shouldRejectNullIATACodeOnFind() {
        AirportRepository repository = new InMemoryAirportRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.findByIATACode(null));
    }

    @Test
    void shouldRejectNullICAOCodeOnFind() {
        AirportRepository repository = new InMemoryAirportRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.findByICAOCode(null));
    }

    @Test
    void shouldRejectNullIATACodeOnExists() {
        AirportRepository repository = new InMemoryAirportRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.existsByIATACode(null));
    }

    @Test
    void shouldRejectNullICAOCodeOnExists() {
        AirportRepository repository = new InMemoryAirportRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.existsByICAOCode(null));
    }
}