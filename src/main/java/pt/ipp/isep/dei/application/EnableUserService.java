package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.UserRepository;

public class EnableUserService {

    private final UserRepository userRepository;

    public EnableUserService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        this.userRepository = userRepository;
    }

    public User enableUser(String emailValue) {
        Email email = new Email(emailValue);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist."));

        user.enable();

        return userRepository.save(user);
    }
}