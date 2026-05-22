package pt.ipp.isep.dei.domain.aircraft.engine;

import java.util.Objects;

public class FuelType {

    private final String name;

    public FuelType(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Fuel type name cannot be empty.");
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

        if (!(other instanceof FuelType that)) {
            return false;
        }

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// Examples are:
// JET_A
// JET_A1
// AVGAS
// SAF
// ELECTRIC
// HYDROGEN