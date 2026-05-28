package pt.ipp.isep.dei.domain.aircraft.model;

import java.util.Objects;

public class AircraftType {

    private final String name;

    public AircraftType(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Aircraft type name cannot be empty.");
        }

        this.name = name.trim().toUpperCase().replace(" ", "_");
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AircraftType that)) {
            return false;
        }

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}