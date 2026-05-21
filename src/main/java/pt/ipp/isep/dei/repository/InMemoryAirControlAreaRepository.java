package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryAirControlAreaRepository implements AirControlAreaRepository {

    private final HashMap<AreaCode, AirControlArea> areasByCode = new HashMap<>();

    @Override
    public AirControlArea save(AirControlArea airControlArea) {
        if (airControlArea == null) {
            throw new IllegalArgumentException("Air control area cannot be null.");
        }

        areasByCode.put(airControlArea.code(), airControlArea);
        return airControlArea;
    }

    @Override
    public Optional<AirControlArea> findByCode(AreaCode code) {
        if (code == null) {
            throw new IllegalArgumentException("Area code cannot be null.");
        }

        return Optional.ofNullable(areasByCode.get(code));
    }

    @Override
    public boolean existsByCode(AreaCode code) {
        if (code == null) {
            throw new IllegalArgumentException("Area code cannot be null.");
        }

        return areasByCode.containsKey(code);
    }

    @Override
    public List<AirControlArea> findAll() {
        return new ArrayList<>(areasByCode.values());
    }
}