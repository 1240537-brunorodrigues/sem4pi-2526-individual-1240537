package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.auth.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryRoleRepository implements RoleRepository {

    private final HashMap<String, Role> rolesByName = new HashMap<>();

    @Override
    public Role save(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }

        rolesByName.put(normalize(role.name()), role);
        return role;
    }

    @Override
    public Optional<Role> findByName(String name) {
        validateName(name);

        return Optional.ofNullable(rolesByName.get(normalize(name)));
    }

    @Override
    public boolean existsByName(String name) {
        validateName(name);

        return rolesByName.containsKey(normalize(name));
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(rolesByName.values());
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Role name cannot be empty.");
        }
    }

    private String normalize(String name) {
        return name.trim().toUpperCase().replace(" ", "_");
    }
}