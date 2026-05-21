package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.CreateAirportRequest;
import pt.ipp.isep.dei.application.CreateAirportService;
import pt.ipp.isep.dei.domain.airport.Airport;

public class CreateAirportController {

    private final CreateAirportService createAirportService;

    public CreateAirportController(CreateAirportService createAirportService) {
        if (createAirportService == null) {
            throw new IllegalArgumentException("Create airport service cannot be null.");
        }

        this.createAirportService = createAirportService;
    }

    public Airport createAirport(CreateAirportRequest request) {
        return createAirportService.createAirport(request);
    }
}