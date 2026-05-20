package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);

    List<User> findAll();
}