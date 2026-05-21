package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.UserRepository;

import java.util.Comparator;
import java.util.List;

public class ListUsersService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    public ListUsersService(UserRepository userRepository, AuthorizationService authorizationService) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
    }

    public List<User> listUsers() {
        authorizationService.requirePermission("LIST_USERS");

        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(user -> user.email().value()))
                .toList();
    }
}