package pt.ipp.isep.dei.domain.aircontrol;

import java.util.Objects;
import java.util.regex.Pattern;

public class AreaCode {

    private static final Pattern AREA_CODE_PATTERN = Pattern.compile("^[A-Z]{2,10}$");

    private final String value;

    public AreaCode(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Area code cannot be empty.");
        }

        String normalizedValue = value.trim().toUpperCase();

        if (!AREA_CODE_PATTERN.matcher(normalizedValue).matches()) {
            throw new IllegalArgumentException("Invalid area code format.");
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

        if (!(other instanceof AreaCode areaCode)) {
            return false;
        }

        return value.equals(areaCode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}