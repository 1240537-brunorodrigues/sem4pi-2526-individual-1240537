package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.AuthenticationService;
import pt.ipp.isep.dei.domain.user.User;

public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        if (authenticationService == null) {
            throw new IllegalArgumentException("Authentication service cannot be null.");
        }

        this.authenticationService = authenticationService;
    }

    public User login(String email, String password) {
        return authenticationService.authenticate(email, password);
    }

    public void logout() {
        authenticationService.logout();
    }

    public boolean isLoggedIn() {
        return authenticationService.isLoggedIn();
    }

    public User currentUser() {
        return authenticationService.currentUser();
    }
}