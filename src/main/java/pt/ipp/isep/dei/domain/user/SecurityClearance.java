package pt.ipp.isep.dei.domain.user;

import java.time.LocalDate;
import java.util.Objects;

public class SecurityClearance {

    private final LocalDate expirationDate;

    public SecurityClearance(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Security clearance expiration date cannot be null.");
        }

        this.expirationDate = expirationDate;
    }

    public LocalDate expirationDate() {
        return expirationDate;
    }

    public boolean isValidOn(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        return !expirationDate.isBefore(date);
    }

    public boolean isExpiredOn(LocalDate date) {
        return !isValidOn(date);
    }

    @Override
    public String toString() {
        return "SecurityClearance{expirationDate=" + expirationDate + '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof SecurityClearance that)) {
            return false;
        }

        return expirationDate.equals(that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expirationDate);
    }
}