package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.UserRepository;

public class DisableUserService {

    private final UserRepository userRepository;

    public DisableUserService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        this.userRepository = userRepository;
    }

    public User disableUser(String emailValue) {
        Email email = new Email(emailValue);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist."));

        user.disable();

        return userRepository.save(user);
    }
}