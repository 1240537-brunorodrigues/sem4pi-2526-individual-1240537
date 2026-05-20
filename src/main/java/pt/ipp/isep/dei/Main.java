package pt.ipp.isep.dei;

import pt.ipp.isep.dei.bootstrap.Bootstrap;
import pt.ipp.isep.dei.repository.InMemoryRoleRepository;
import pt.ipp.isep.dei.repository.InMemoryUserRepository;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;
import pt.ipp.isep.dei.ui.console.MainMenuUI;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        RoleRepository roleRepository = new InMemoryRoleRepository();

        Bootstrap bootstrap = new Bootstrap(userRepository, roleRepository);
        bootstrap.run();

        MainMenuUI menu = new MainMenuUI();
        menu.run();
    }
}
