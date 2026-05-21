package pt.ipp.isep.dei.domain.airport;

import java.util.Objects;
import java.util.regex.Pattern;

public class IATACode {

    private static final Pattern IATA_PATTERN = Pattern.compile("^[A-Z]{3}$");

    private final String value;

    public IATACode(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("IATA code cannot be empty.");
        }

        String normalizedValue = value.trim().toUpperCase();

        if (!IATA_PATTERN.matcher(normalizedValue).matches()) {
            throw new IllegalArgumentException("IATA code must have exactly three letters.");
        }

        this.value = normalizedValue;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof IATACode iataCode)) {
            return false;
        }

        return value.equals(iataCode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}