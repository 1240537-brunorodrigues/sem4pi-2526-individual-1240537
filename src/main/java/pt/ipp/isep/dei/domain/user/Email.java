package pt.ipp.isep.dei.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        String normalizedValue = value.trim().toLowerCase();

        if (!EMAIL_PATTERN.matcher(normalizedValue).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
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

        if (!(other instanceof Email email)) {
            return false;
        }

        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}