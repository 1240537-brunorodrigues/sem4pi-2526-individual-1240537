package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.airport.Airport;
import pt.ipp.isep.dei.domain.airport.IATACode;
import pt.ipp.isep.dei.domain.airport.ICAOCode;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {

    Airport save(Airport airport);

    Optional<Airport> findByIATACode(IATACode iataCode);

    Optional<Airport> findByICAOCode(ICAOCode icaoCode);

    boolean existsByIATACode(IATACode iataCode);

    boolean existsByICAOCode(ICAOCode icaoCode);

    List<Airport> findAll();
}