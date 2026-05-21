package pt.ipp.isep.dei.domain.geo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Country {

    private static final Pattern COUNTRY_CODE_PATTERN = Pattern.compile("^[A-Z]{2}$");

    private final String name;
    private final String code;

    public Country(String name, String code) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Country name cannot be empty.");
        }

        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Country code cannot be empty.");
        }

        String normalizedCode = code.trim().toUpperCase();

        if (!COUNTRY_CODE_PATTERN.matcher(normalizedCode).matches()) {
            throw new IllegalArgumentException("Country code must have exactly two letters.");
        }

        this.name = name.trim();
        this.code = normalizedCode;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Country country)) {
            return false;
        }

        return code.equals(country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}