package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.RegisterUserRequest;
import pt.ipp.isep.dei.application.RegisterUserService;
import pt.ipp.isep.dei.domain.user.User;

public class RegisterUserController {

    private final RegisterUserService registerUserService;

    public RegisterUserController(RegisterUserService registerUserService) {
        if (registerUserService == null) {
            throw new IllegalArgumentException("Register user service cannot be null.");
        }

        this.registerUserService = registerUserService;
    }

    public User registerUser(RegisterUserRequest request) {
        return registerUserService.registerUser(request);
    }
}