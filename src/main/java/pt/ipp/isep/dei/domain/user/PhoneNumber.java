package pt.ipp.isep.dei.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class PhoneNumber {

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^(\\+\\d{1,3})?\\d{9,15}$"
    );

    private final String value;

    public PhoneNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        String normalizedValue = value
                .trim()
                .replace(" ", "")
                .replace("-", "");

        if (!PHONE_PATTERN.matcher(normalizedValue).matches()) {
            throw new IllegalArgumentException("Invalid phone number format.");
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

        if (!(other instanceof PhoneNumber phoneNumber)) {
            return false;
        }

        return value.equals(phoneNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}