package pt.ipp.isep.dei.application;

import java.util.List;

public class RegisterAirControlAreaRequest {

    private final String code;
    private final String name;
    private final List<CoordinateRequest> boundaryCoordinates;

    public RegisterAirControlAreaRequest(
            String code,
            String name,
            List<CoordinateRequest> boundaryCoordinates
    ) {
        this.code = code;
        this.name = name;
        this.boundaryCoordinates = boundaryCoordinates;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public List<CoordinateRequest> boundaryCoordinates() {
        return boundaryCoordinates;
    }

    public static class CoordinateRequest {

        private final double latitude;
        private final double longitude;

        public CoordinateRequest(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double latitude() {
            return latitude;
        }

        public double longitude() {
            return longitude;
        }
    }
}