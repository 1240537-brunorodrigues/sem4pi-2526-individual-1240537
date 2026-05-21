package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.airport.Airport;
import pt.ipp.isep.dei.domain.airport.IATACode;
import pt.ipp.isep.dei.domain.airport.ICAOCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryAirportRepository implements AirportRepository {

    private final HashMap<IATACode, Airport> airportsByIATACode = new HashMap<>();
    private final HashMap<ICAOCode, Airport> airportsByICAOCode = new HashMap<>();

    @Override
    public Airport save(Airport airport) {
        if (airport == null) {
            throw new IllegalArgumentException("Airport cannot be null.");
        }

        airportsByIATACode.put(airport.iataCode(), airport);
        airportsByICAOCode.put(airport.icaoCode(), airport);

        return airport;
    }

    @Override
    public Optional<Airport> findByIATACode(IATACode iataCode) {
        if (iataCode == null) {
            throw new IllegalArgumentException("IATA code cannot be null.");
        }

        return Optional.ofNullable(airportsByIATACode.get(iataCode));
    }

    @Override
    public Optional<Airport> findByICAOCode(ICAOCode icaoCode) {
        if (icaoCode == null) {
            throw new IllegalArgumentException("ICAO code cannot be null.");
        }

        return Optional.ofNullable(airportsByICAOCode.get(icaoCode));
    }

    @Override
    public boolean existsByIATACode(IATACode iataCode) {
        if (iataCode == null) {
            throw new IllegalArgumentException("IATA code cannot be null.");
        }

        return airportsByIATACode.containsKey(iataCode);
    }

    @Override
    public boolean existsByICAOCode(ICAOCode icaoCode) {
        if (icaoCode == null) {
            throw new IllegalArgumentException("ICAO code cannot be null.");
        }

        return airportsByICAOCode.containsKey(icaoCode);
    }

    @Override
    public List<Airport> findAll() {
        return new ArrayList<>(airportsByIATACode.values());
    }
}