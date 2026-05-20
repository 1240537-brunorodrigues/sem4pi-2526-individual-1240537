package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.user.User;

public class AuthenticatedUserSession {

    private User currentUser;

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User currentUser() {
        if (!isLoggedIn()) {
            throw new IllegalStateException("No user is currently authenticated.");
        }

        return currentUser;
    }

    public void login(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }
}