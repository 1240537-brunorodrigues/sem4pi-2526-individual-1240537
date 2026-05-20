package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.application.ListUsersService;
import pt.ipp.isep.dei.domain.user.User;

import java.util.List;

public class ListUsersController {

    private final ListUsersService listUsersService;

    public ListUsersController(ListUsersService listUsersService) {
        if (listUsersService == null) {
            throw new IllegalArgumentException("List users service cannot be null.");
        }

        this.listUsersService = listUsersService;
    }

    public List<User> listUsers() {
        return listUsersService.listUsers();
    }
}