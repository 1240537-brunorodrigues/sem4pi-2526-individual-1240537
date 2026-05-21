package pt.ipp.isep.dei.domain.aircontrol;

import pt.ipp.isep.dei.domain.customer.Customer;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;

import java.util.Objects;

public class AirControlArea extends Customer {

    private final AreaCode code;
    private final GeographicBoundary geographicBoundary;

    public AirControlArea(AreaCode code, String name, GeographicBoundary geographicBoundary) {
        super(name);

        if (code == null) {
            throw new IllegalArgumentException("Air control area code cannot be null.");
        }

        if (geographicBoundary == null) {
            throw new IllegalArgumentException("Geographic boundary cannot be null.");
        }

        this.code = code;
        this.geographicBoundary = geographicBoundary;
    }

    public AreaCode code() {
        return code;
    }

    public GeographicBoundary geographicBoundary() {
        return geographicBoundary;
    }

    @Override
    public String toString() {
        return "AirControlArea{" +
                "code=" + code +
                ", name='" + name() + '\'' +
                ", geographicBoundary=" + geographicBoundary +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AirControlArea that)) {
            return false;
        }

        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}