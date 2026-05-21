package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;
import pt.ipp.isep.dei.repository.AirControlAreaRepository;

import java.util.List;

public class RegisterAirControlAreaService {

    private final AirControlAreaRepository airControlAreaRepository;
    private final AuthorizationService authorizationService;

    public RegisterAirControlAreaService(
            AirControlAreaRepository airControlAreaRepository,
            AuthorizationService authorizationService
    ) {
        if (airControlAreaRepository == null) {
            throw new IllegalArgumentException("Air control area repository cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        this.airControlAreaRepository = airControlAreaRepository;
        this.authorizationService = authorizationService;
    }

    public AirControlArea registerAirControlArea(RegisterAirControlAreaRequest request) {
        authorizationService.requirePermission("REGISTER_AIR_CONTROL_AREA");

        if (request == null) {
            throw new IllegalArgumentException("Register air control area request cannot be null.");
        }

        AreaCode code = new AreaCode(request.code());

        if (airControlAreaRepository.existsByCode(code)) {
            throw new IllegalArgumentException("An air control area with this code already exists.");
        }

        GeographicBoundary boundary = buildBoundary(request.boundaryCoordinates());

        AirControlArea airControlArea = new AirControlArea(
                code,
                request.name(),
                boundary
        );

        return airControlAreaRepository.save(airControlArea);
    }

    private GeographicBoundary buildBoundary(List<RegisterAirControlAreaRequest.CoordinateRequest> coordinateRequests) {
        if (coordinateRequests == null) {
            throw new IllegalArgumentException("Boundary coordinates cannot be null.");
        }

        List<Coordinate> coordinates = coordinateRequests.stream()
                .map(coordinateRequest -> {
                    if (coordinateRequest == null) {
                        throw new IllegalArgumentException("Boundary coordinates cannot contain null values.");
                    }

                    return new Coordinate(
                            coordinateRequest.latitude(),
                            coordinateRequest.longitude()
                    );
                })
                .toList();

        return new GeographicBoundary(coordinates);
    }
}