package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.airport.Airport;
import pt.ipp.isep.dei.domain.airport.IATACode;
import pt.ipp.isep.dei.domain.airport.ICAOCode;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.Country;
import pt.ipp.isep.dei.repository.AirControlAreaRepository;
import pt.ipp.isep.dei.repository.AirportRepository;

public class CreateAirportService {

    private final AirportRepository airportRepository;
    private final AirControlAreaRepository airControlAreaRepository;
    private final AuthorizationService authorizationService;

    public CreateAirportService(
            AirportRepository airportRepository,
            AirControlAreaRepository airControlAreaRepository,
            AuthorizationService authorizationService
    ) {
        if (airportRepository == null) {
            throw new IllegalArgumentException("Airport repository cannot be null.");
        }

        if (airControlAreaRepository == null) {
            throw new IllegalArgumentException("Air control area repository cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        this.airportRepository = airportRepository;
        this.airControlAreaRepository = airControlAreaRepository;
        this.authorizationService = authorizationService;
    }

    public Airport createAirport(CreateAirportRequest request) {
        authorizationService.requirePermission("CREATE_AIRPORT");

        if (request == null) {
            throw new IllegalArgumentException("Create airport request cannot be null.");
        }

        IATACode iataCode = new IATACode(request.iataCode());
        ICAOCode icaoCode = new ICAOCode(request.icaoCode());

        if (airportRepository.existsByIATACode(iataCode)) {
            throw new IllegalArgumentException("An airport with this IATA code already exists.");
        }

        if (airportRepository.existsByICAOCode(icaoCode)) {
            throw new IllegalArgumentException("An airport with this ICAO code already exists.");
        }

        AreaCode areaCode = new AreaCode(request.airControlAreaCode());

        AirControlArea airControlArea = airControlAreaRepository.findByCode(areaCode)
                .orElseThrow(() -> new IllegalArgumentException("Air control area does not exist."));

        Airport airport = new Airport(
                request.name(),
                request.town(),
                new Country(request.countryName(), request.countryCode()),
                request.elevation(),
                iataCode,
                icaoCode,
                new Coordinate(request.latitude(), request.longitude()),
                airControlArea
        );

        return airportRepository.save(airport);
    }
}