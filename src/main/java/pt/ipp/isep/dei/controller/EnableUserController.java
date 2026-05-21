package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.EnableUserService;
import pt.ipp.isep.dei.domain.user.User;

public class EnableUserController {

    private final EnableUserService enableUserService;

    public EnableUserController(EnableUserService enableUserService) {
        if (enableUserService == null) {
            throw new IllegalArgumentException("Enable user service cannot be null.");
        }

        this.enableUserService = enableUserService;
    }

    public User enableUser(String email) {
        return enableUserService.enableUser(email);
    }
}