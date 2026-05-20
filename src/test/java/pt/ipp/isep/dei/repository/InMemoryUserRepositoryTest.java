package pt.ipp.isep.dei.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.*;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    private User createUser(String email) {
        return new User(
                new Email(email),
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(new Role("ADMINISTRATOR")),
                new SecurityClearance(LocalDate.of(2027, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        );
    }

    @Test
    void shouldSaveUser() {
        UserRepository repository = new InMemoryUserRepository();
        User user = createUser("admin@alsafe.pt");

        User savedUser = repository.save(user);

        assertEquals(user, savedUser);
        assertTrue(repository.existsByEmail(new Email("admin@alsafe.pt")));
    }

    @Test
    void shouldFindUserByEmail() {
        UserRepository repository = new InMemoryUserRepository();
        User user = createUser("admin@alsafe.pt");

        repository.save(user);

        assertTrue(repository.findByEmail(new Email("admin@alsafe.pt")).isPresent());
        assertEquals(user, repository.findByEmail(new Email("admin@alsafe.pt")).orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenUserDoesNotExist() {
        UserRepository repository = new InMemoryUserRepository();

        assertTrue(repository.findByEmail(new Email("missing@alsafe.pt")).isEmpty());
    }

    @Test
    void shouldReturnAllUsers() {
        UserRepository repository = new InMemoryUserRepository();

        repository.save(createUser("admin@alsafe.pt"));
        repository.save(createUser("operator@alsafe.pt"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldReplaceUserWithSameEmail() {
        UserRepository repository = new InMemoryUserRepository();

        repository.save(createUser("admin@alsafe.pt"));
        repository.save(createUser("ADMIN@ALSAFE.PT"));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldRejectNullUser() {
        UserRepository repository = new InMemoryUserRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    void shouldRejectNullEmailOnFind() {
        UserRepository repository = new InMemoryUserRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.findByEmail(null));
    }

    @Test
    void shouldRejectNullEmailOnExists() {
        UserRepository repository = new InMemoryUserRepository();

        assertThrows(IllegalArgumentException.class, () -> repository.existsByEmail(null));
    }
}