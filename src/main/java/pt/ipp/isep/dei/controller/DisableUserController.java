package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.DisableUserService;
import pt.ipp.isep.dei.domain.user.User;

public class DisableUserController {

    private final DisableUserService disableUserService;

    public DisableUserController(DisableUserService disableUserService) {
        if (disableUserService == null) {
            throw new IllegalArgumentException("Disable user service cannot be null.");
        }

        this.disableUserService = disableUserService;
    }

    public User disableUser(String email) {
        return disableUserService.disableUser(email);
    }
}