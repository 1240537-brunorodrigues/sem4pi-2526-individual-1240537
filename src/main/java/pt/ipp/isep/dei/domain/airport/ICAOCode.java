package pt.ipp.isep.dei.domain.airport;

import java.util.Objects;
import java.util.regex.Pattern;

public class ICAOCode {

    private static final Pattern ICAO_PATTERN = Pattern.compile("^[A-Z]{4}$");

    private final String value;

    public ICAOCode(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ICAO code cannot be empty.");
        }

        String normalizedValue = value.trim().toUpperCase();

        if (!ICAO_PATTERN.matcher(normalizedValue).matches()) {
            throw new IllegalArgumentException("ICAO code must have exactly four letters.");
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

        if (!(other instanceof ICAOCode icaoCode)) {
            return false;
        }

        return value.equals(icaoCode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}