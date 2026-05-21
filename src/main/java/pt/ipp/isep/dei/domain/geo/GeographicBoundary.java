package pt.ipp.isep.dei.domain.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GeographicBoundary {

    private final List<Coordinate> coordinates = new ArrayList<>();

    public GeographicBoundary(List<Coordinate> coordinates) {
        if (coordinates == null || coordinates.size() < 3) {
            throw new IllegalArgumentException("A geographic boundary must have at least three coordinates.");
        }

        if (coordinates.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Geographic boundary coordinates cannot contain null values.");
        }

        this.coordinates.addAll(coordinates);
    }

    public List<Coordinate> coordinates() {
        return Collections.unmodifiableList(coordinates);
    }

    public int size() {
        return coordinates.size();
    }

    @Override
    public String toString() {
        return coordinates.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof GeographicBoundary that)) {
            return false;
        }

        return coordinates.equals(that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}