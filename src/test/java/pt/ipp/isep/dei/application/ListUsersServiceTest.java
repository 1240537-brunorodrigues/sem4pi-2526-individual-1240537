package pt.ipp.isep.dei.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ListUsersServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticatedUserSession session;
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private ListUsersService service;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        session = new AuthenticatedUserSession();
        authenticationService = new AuthenticationService(userRepository, session);
        authorizationService = new AuthorizationService(session);

        authenticationService.authenticate("admin@alsafe.pt", "Password123");

        service = new ListUsersService(userRepository, authorizationService);
    }

    private User createUser(String email) {
        Role role = roleRepository.findByName("BACKOFFICE_OPERATOR").orElseThrow();

        return new User(
                new Email(email),
                "Test User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(role),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.of(2026, 1, 1), 12)
        );
    }

    @Test
    void shouldListExistingUsers() {
        var users = service.listUsers();

        assertEquals(1, users.size());
        assertEquals(new Email("admin@alsafe.pt"), users.get(0).email());
    }

    @Test
    void shouldListAllUsers() {
        userRepository.save(createUser("bruno@alsafe.pt"));
        userRepository.save(createUser("ana@alsafe.pt"));

        var users = service.listUsers();

        assertEquals(3, users.size());
    }

    @Test
    void shouldReturnUsersSortedByEmail() {
        userRepository.save(createUser("zeta@alsafe.pt"));
        userRepository.save(createUser("ana@alsafe.pt"));

        var users = service.listUsers();

        assertEquals(new Email("admin@alsafe.pt"), users.get(0).email());
        assertEquals(new Email("ana@alsafe.pt"), users.get(1).email());
        assertEquals(new Email("zeta@alsafe.pt"), users.get(2).email());
    }

    @Test
    void shouldRejectListUsersWhenNotAuthenticated() {
        session.logout();

        assertThrows(SecurityException.class, () -> service.listUsers());
    }

    @Test
    void shouldRejectListUsersWhenUserDoesNotHavePermission() {
        User weatherUser = new User(
                new Email("weather@alsafe.pt"),
                "Weather User",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(roleRepository.findByName("WEATHER_PERSON").orElseThrow()),
                new SecurityClearance(LocalDate.of(2028, 12, 31)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        userRepository.save(weatherUser);

        authenticationService.logout();
        authenticationService.authenticate("weather@alsafe.pt", "Password123");

        assertThrows(SecurityException.class, () -> service.listUsers());
    }

    @Test
    void shouldRejectNullRepository() {
        assertThrows(IllegalArgumentException.class,
                () -> new ListUsersService(null, authorizationService));
    }

    @Test
    void shouldRejectNullAuthorizationService() {
        assertThrows(IllegalArgumentException.class,
                () -> new ListUsersService(userRepository, null));
    }
}