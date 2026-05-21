package pt.ipp.isep.dei.domain.airport;

import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.Country;

import java.util.Objects;

public class Airport {

    private final String name;
    private final String town;
    private final Country country;
    private final double elevation;
    private final IATACode iataCode;
    private final ICAOCode icaoCode;
    private final Coordinate coordinate;
    private final AirControlArea airControlArea;

    public Airport(
            String name,
            String town,
            Country country,
            double elevation,
            IATACode iataCode,
            ICAOCode icaoCode,
            Coordinate coordinate,
            AirControlArea airControlArea
    ) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Airport name cannot be empty.");
        }

        if (town == null || town.isBlank()) {
            throw new IllegalArgumentException("Airport town cannot be empty.");
        }

        if (country == null) {
            throw new IllegalArgumentException("Airport country cannot be null.");
        }

        if (elevation < 0) {
            throw new IllegalArgumentException("Airport elevation cannot be negative.");
        }

        if (iataCode == null) {
            throw new IllegalArgumentException("IATA code cannot be null.");
        }

        if (icaoCode == null) {
            throw new IllegalArgumentException("ICAO code cannot be null.");
        }

        if (coordinate == null) {
            throw new IllegalArgumentException("Airport coordinate cannot be null.");
        }

        if (airControlArea == null) {
            throw new IllegalArgumentException("Air control area cannot be null.");
        }

        this.name = name.trim();
        this.town = town.trim();
        this.country = country;
        this.elevation = elevation;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.coordinate = coordinate;
        this.airControlArea = airControlArea;
    }

    public String name() {
        return name;
    }

    public String town() {
        return town;
    }

    public Country country() {
        return country;
    }

    public double elevation() {
        return elevation;
    }

    public IATACode iataCode() {
        return iataCode;
    }

    public ICAOCode icaoCode() {
        return icaoCode;
    }

    public Coordinate coordinate() {
        return coordinate;
    }

    public AirControlArea airControlArea() {
        return airControlArea;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", town='" + town + '\'' +
                ", country=" + country +
                ", elevation=" + elevation +
                ", iataCode=" + iataCode +
                ", icaoCode=" + icaoCode +
                ", coordinate=" + coordinate +
                ", airControlArea=" + airControlArea.code() +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Airport airport)) {
            return false;
        }

        return iataCode.equals(airport.iataCode)
                || icaoCode.equals(airport.icaoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, icaoCode);
    }
}