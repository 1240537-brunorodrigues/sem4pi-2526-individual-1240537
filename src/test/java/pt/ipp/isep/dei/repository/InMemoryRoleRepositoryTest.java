package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.auth.Role;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRoleRepositoryTest {

    @Test
    void shouldSaveRole() {
        RoleRepository repository = new InMemoryRoleRepository();
        Role role = new Role("ADMINISTRATOR");

        Role savedRole = repository.save(role);

        assertEquals(role, savedRole);
        assertTrue(repository.existsByName("ADMINISTRATOR"));
    }

    @Test
    void shouldFindRoleByName() {
        RoleRepository repository = new InMemoryRoleRepository();
        Role role = new Role("BACKOFFICE_OPERATOR");

        repository.save(role);

        assertTrue(repository.findByName("BACKOFFICE_OPERATOR").isPresent());
        assertEquals(role, repository.findByName("backoffice operator").orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenRoleDoesNotExist() {
        RoleRepository repository = new InMemoryRoleRepository();

        assertTrue(repository.findByName("MISSING_ROLE").isEmpty());
    }

    @Test
    void shouldReturnAllRoles() {
        RoleRepository repository = new InMemoryRoleRepository();

        repository.save(new Role("ADMINISTRATOR"));
        repository.save(new Role("BACKOFFICE_OPERATOR"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldReplaceRoleWithSameName() {
        RoleRepository repository = new InMemoryRoleRepository();

        repository.save(new Role("BACKOFFICE_OPERATOR"));
        repository.save(new Role("backoffice operator"));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldRejectNullRole() {
        RoleRepository repository = new InMemoryRoleRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    void shouldRejectNullNameOnFind() {
        RoleRepository repository = new InMemoryRoleRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.findByName(null));
    }

    @Test
    void shouldRejectBlankNameOnFind() {
        RoleRepository repository = new InMemoryRoleRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.findByName("   "));
    }

    @Test
    void shouldRejectNullNameOnExists() {
        RoleRepository repository = new InMemoryRoleRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.existsByName(null));
    }

    @Test
    void shouldRejectBlankNameOnExists() {
        RoleRepository repository = new InMemoryRoleRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.existsByName("   "));
    }
}