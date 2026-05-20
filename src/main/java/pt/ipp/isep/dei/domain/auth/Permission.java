package pt.ipp.isep.dei.domain.auth;

import java.util.Objects;

public class Permission {

    private final String name;

    public Permission(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Permission name cannot be empty.");
        }

        this.name = normalize(name);
    }

    private String normalize(String value) {
        return value.trim().toUpperCase().replace(" ", "_");
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

        if (!(other instanceof Permission permission)) {
            return false;
        }

        return name.equals(permission.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}