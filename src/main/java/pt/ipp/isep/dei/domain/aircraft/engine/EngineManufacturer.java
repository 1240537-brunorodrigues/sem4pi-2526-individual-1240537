package pt.ipp.isep.dei.domain.aircraft.engine;

import java.util.Objects;

public class EngineManufacturer {

    private final String name;
    private final String country;

    public EngineManufacturer(String name, String country) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Engine manufacturer name cannot be empty.");
        }

        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Engine manufacturer country cannot be empty.");
        }

        this.name = name.trim();
        this.country = country.trim();
    }

    public String name() {
        return name;
    }

    public String country() {
        return country;
    }

    @Override
    public String toString() {
        return name + " (" + country + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof EngineManufacturer that)) {
            return false;
        }

        return name.equalsIgnoreCase(that.name)
                && country.equalsIgnoreCase(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), country.toLowerCase());
    }
}