package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    private final HashMap<Email, User> usersByEmail = new HashMap<>();

    @Override
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        usersByEmail.put(user.email(), user);
        return user;
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        return Optional.ofNullable(usersByEmail.get(email));
    }

    @Override
    public boolean existsByEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        return usersByEmail.containsKey(email);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(usersByEmail.values());
    }
}