package pt.ipp.isep.dei.domain.customer;

import java.util.Objects;
import java.util.UUID;

public abstract class Customer {

    private final String id;
    private final String name;

    protected Customer(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }

        this.id = UUID.randomUUID().toString();
        this.name = name.trim();
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Customer customer)) {
            return false;
        }

        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}