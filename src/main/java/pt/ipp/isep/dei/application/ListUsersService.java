package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.UserRepository;

import java.util.Comparator;
import java.util.List;

public class ListUsersService {

    private final UserRepository userRepository;

    public ListUsersService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        this.userRepository = userRepository;
    }

    public List<User> listUsers() {
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(user -> user.email().value()))
                .toList();
    }
}