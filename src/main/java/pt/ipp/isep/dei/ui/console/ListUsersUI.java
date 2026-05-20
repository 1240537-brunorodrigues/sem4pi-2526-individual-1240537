package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.ListUsersController;
import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.User;

import java.util.Comparator;
import java.util.stream.Collectors;

public class ListUsersUI {

    private final ListUsersController controller;

    public ListUsersUI(ListUsersController controller) {
        if (controller == null) {
            throw new IllegalArgumentException("List users controller cannot be null.");
        }

        this.controller = controller;
    }

    public void run() {
        System.out.println("=================================");
        System.out.println("            List Users           ");
        System.out.println("=================================");

        var users = controller.listUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {
            displayUser(user);
        }
    }

    private void displayUser(User user) {
        String roles = user.roles()
                .stream()
                .sorted(Comparator.comparing(Role::name))
                .map(Role::name)
                .collect(Collectors.joining(", "));

        System.out.println("---------------------------------");
        System.out.println("Email: " + user.email());
        System.out.println("Name: " + user.name());
        System.out.println("Phone: " + user.phoneNumber());
        System.out.println("Status: " + user.status());
        System.out.println("Roles: " + roles);
    }
}