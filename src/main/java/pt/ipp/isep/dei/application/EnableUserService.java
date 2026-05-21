package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.UserRepository;

public class EnableUserService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    public EnableUserService(UserRepository userRepository, AuthorizationService authorizationService) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        if (authorizationService == null) {
            throw new IllegalArgumentException("Authorization service cannot be null.");
        }

        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
    }

    public User enableUser(String emailValue) {
        authorizationService.requirePermission("ENABLE_USER");

        Email email = new Email(emailValue);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist."));

        user.enable();

        return userRepository.save(user);
    }
}