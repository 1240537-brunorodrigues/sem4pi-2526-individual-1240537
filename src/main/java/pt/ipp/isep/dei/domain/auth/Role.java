package pt.ipp.isep.dei.domain.auth;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Role {

    private final String name;
    private final Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Role name cannot be empty.");
        }

        this.name = normalize(name);
    }

    private String normalize(String value) {
        return value.trim().toUpperCase().replace(" ", "_");
    }

    public String name() {
        return name;
    }

    public void addPermission(Permission permission) {
        if (permission == null) {
            throw new IllegalArgumentException("Permission cannot be null.");
        }

        permissions.add(permission);
    }

    public boolean hasPermission(Permission permission) {
        if (permission == null) {
            return false;
        }

        return permissions.contains(permission);
    }

    public Set<Permission> permissions() {
        return Collections.unmodifiableSet(permissions);
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

        if (!(other instanceof Role role)) {
            return false;
        }

        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}