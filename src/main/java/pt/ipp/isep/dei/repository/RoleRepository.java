package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.auth.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Role save(Role role);

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

    List<Role> findAll();
}