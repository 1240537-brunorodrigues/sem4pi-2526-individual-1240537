package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;

import java.util.List;
import java.util.Optional;

public interface AirControlAreaRepository {

    AirControlArea save(AirControlArea airControlArea);

    Optional<AirControlArea> findByCode(AreaCode code);

    boolean existsByCode(AreaCode code);

    List<AirControlArea> findAll();
}