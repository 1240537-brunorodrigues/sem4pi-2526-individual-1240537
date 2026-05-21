package pt.ipp.isep.dei.application;

public class CreateAirportRequest {

    private final String name;
    private final String town;
    private final String countryName;
    private final String countryCode;
    private final double elevation;
    private final String iataCode;
    private final String icaoCode;
    private final double latitude;
    private final double longitude;
    private final String airControlAreaCode;

    public CreateAirportRequest(
            String name,
            String town,
            String countryName,
            String countryCode,
            double elevation,
            String iataCode,
            String icaoCode,
            double latitude,
            double longitude,
            String airControlAreaCode
    ) {
        this.name = name;
        this.town = town;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.elevation = elevation;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.airControlAreaCode = airControlAreaCode;
    }

    public String name() {
        return name;
    }

    public String town() {
        return town;
    }

    public String countryName() {
        return countryName;
    }

    public String countryCode() {
        return countryCode;
    }

    public double elevation() {
        return elevation;
    }

    public String iataCode() {
        return iataCode;
    }

    public String icaoCode() {
        return icaoCode;
    }

    public double latitude() {
        return latitude;
    }

    public double longitude() {
        return longitude;
    }

    public String airControlAreaCode() {
        return airControlAreaCode;
    }
}