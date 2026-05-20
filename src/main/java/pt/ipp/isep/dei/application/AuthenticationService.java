package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.user.Email;
import pt.ipp.isep.dei.domain.user.User;
import pt.ipp.isep.dei.repository.UserRepository;

public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticatedUserSession session;

    public AuthenticationService(UserRepository userRepository, AuthenticatedUserSession session) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        if (session == null) {
            throw new IllegalArgumentException("Authenticated user session cannot be null.");
        }

        this.userRepository = userRepository;
        this.session = session;
    }

    public User authenticate(String emailValue, String password) {
        Email email = new Email(emailValue);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        if (user.isDisabled()) {
            throw new IllegalArgumentException("User is disabled.");
        }

        if (!user.matchesPassword(password)) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        session.login(user);

        return user;
    }

    public void logout() {
        session.logout();
    }

    public boolean isLoggedIn() {
        return session.isLoggedIn();
    }

    public User currentUser() {
        return session.currentUser();
    }
}