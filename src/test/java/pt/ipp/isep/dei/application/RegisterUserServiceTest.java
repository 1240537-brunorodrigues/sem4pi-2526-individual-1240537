package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RegisterUserService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        service = new RegisterUserService(userRepository, roleRepository);
    }

    private RegisterUserRequest validRequest(String email) {
        return new RegisterUserRequest(
                email,
                "John Doe",
                "+351912345678",
                "Password123",
                Set.of("BACKOFFICE_OPERATOR"),
                LocalDate.of(2028, 12, 31),
                LocalDate.of(2026, 1, 1),
                12
        );
    }

    @Test
    void shouldRegisterValidUser() {
        RegisterUserRequest request = validRequest("john.doe@alsafe.pt");

        User user = service.registerUser(request);

        assertEquals(new Email("john.doe@alsafe.pt"), user.email());
        assertEquals("John Doe", user.name());
        assertTrue(userRepository.existsByEmail(new Email("john.doe@alsafe.pt")));
        assertTrue(user.hasRole(roleRepository.findByName("BACKOFFICE_OPERATOR").orElseThrow()));
    }

    @Test
    void shouldRejectNullRequest() {
        assertThrows(IllegalArgumentException.class, () -> service.registerUser(null));
    }

    @Test
    void shouldRejectDuplicatedEmail() {
        RegisterUserRequest request = validRequest("john.doe@alsafe.pt");

        service.registerUser(request);

        assertThrows(IllegalArgumentException.class, () -> service.registerUser(request));
    }

    @Test
    void shouldRejectUnknownRole() {
        RegisterUserRequest request = new RegisterUserRequest(
                "john.doe@alsafe.pt",
                "John Doe",
                "+351912345678",
                "Password123",
                Set.of("UNKNOWN_ROLE"),
                LocalDate.of(2028, 12, 31),
                LocalDate.of(2026, 1, 1),
                12
        );

        assertThrows(IllegalArgumentException.class, () -> service.registerUser(request));
    }

    @Test
    void shouldRejectEmptyRoles() {
        RegisterUserRequest request = new RegisterUserRequest(
                "john.doe@alsafe.pt",
                "John Doe",
                "+351912345678",
                "Password123",
                Set.of(),
                LocalDate.of(2028, 12, 31),
                LocalDate.of(2026, 1, 1),
                12
        );

        assertThrows(IllegalArgumentException.class, () -> service.registerUser(request));
    }

    @Test
    void shouldRejectInvalidEmail() {
        RegisterUserRequest request = validRequest("invalid-email");

        assertThrows(IllegalArgumentException.class, () -> service.registerUser(request));
    }

    @Test
    void shouldRejectInvalidPhoneNumber() {
        RegisterUserRequest request = new RegisterUserRequest(
                "john.doe@alsafe.pt",
                "John Doe",
                "invalid-phone",
                "Password123",
                Set.of("BACKOFFICE_OPERATOR"),
                LocalDate.of(2028, 12, 31),
                LocalDate.of(2026, 1, 1),
                12
        );

        assertThrows(IllegalArgumentException.class, () -> service.registerUser(request));
    }

    @Test
    void shouldRejectShortPassword() {
        RegisterUserRequest request = new RegisterUserRequest(
                "john.doe@alsafe.pt",
                "John Doe",
                "+351912345678",
                "1234567",
                Set.of("BACKOFFICE_OPERATOR"),
                LocalDate.of(2028, 12, 31),
                LocalDate.of(2026, 1, 1),
                12
        );

        assertThrows(IllegalArgumentException.class, () -> service.registerUser(request));
    }
}