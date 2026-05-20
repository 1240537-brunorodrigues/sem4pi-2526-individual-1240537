package pt.ipp.isep.dei.bootstrap;

import pt.ipp.isep.dei.domain.auth.Permission;
import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

public class Bootstrap {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Bootstrap(UserRepository userRepository, RoleRepository roleRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        if (roleRepository == null) {
            throw new IllegalArgumentException("Role repository cannot be null.");
        }

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void run() {
        createRoles();
        createDefaultAdministrator();
    }

    private void createRoles() {
        createAdministratorRole();
        createBackofficeOperatorRole();
        createWeatherPersonRole();
        createFlightControlOperatorRole();
        createPilotRole();
        createAirTransportCompanyCollaboratorRole();
    }

    private void createAdministratorRole() {
        Role role = new Role("ADMINISTRATOR");

        role.addPermission(new Permission("REGISTER_USER"));
        role.addPermission(new Permission("LIST_USERS"));
        role.addPermission(new Permission("ENABLE_USER"));
        role.addPermission(new Permission("DISABLE_USER"));
        role.addPermission(new Permission("AUTHENTICATE"));

        saveRoleIfMissing(role);
    }

    private void createBackofficeOperatorRole() {
        Role role = new Role("BACKOFFICE_OPERATOR");

        role.addPermission(new Permission("REGISTER_AIR_CONTROL_AREA"));
        role.addPermission(new Permission("CREATE_AIRPORT"));
        role.addPermission(new Permission("CREATE_AIRCRAFT_MODEL"));
        role.addPermission(new Permission("CREATE_AIRCRAFT_ENGINE_MODEL"));
        role.addPermission(new Permission("REGISTER_AIR_TRANSPORT_COMPANY"));

        saveRoleIfMissing(role);
    }

    private void createWeatherPersonRole() {
        Role role = new Role("WEATHER_PERSON");

        role.addPermission(new Permission("REGISTER_WEATHER_DATA"));
        role.addPermission(new Permission("IMPORT_WEATHER_DATA"));
        role.addPermission(new Permission("CONSULT_WEATHER_DATA"));

        saveRoleIfMissing(role);
    }

    private void createFlightControlOperatorRole() {
        Role role = new Role("FLIGHT_CONTROL_OPERATOR");

        role.addPermission(new Permission("START_SIMULATION"));
        role.addPermission(new Permission("GENERATE_SIMULATION_REPORT"));
        role.addPermission(new Permission("GENERATE_FINAL_SIMULATION_REPORT"));
        role.addPermission(new Permission("GENERATE_MONTHLY_REPORT"));

        saveRoleIfMissing(role);
    }

    private void createPilotRole() {
        Role role = new Role("PILOT");

        role.addPermission(new Permission("CREATE_FLIGHT_PLAN"));
        role.addPermission(new Permission("IMPORT_FLIGHT_PLAN"));
        role.addPermission(new Permission("TEST_FLIGHT_PLAN"));

        saveRoleIfMissing(role);
    }

    private void createAirTransportCompanyCollaboratorRole() {
        Role role = new Role("AIR_TRANSPORT_COMPANY_COLLABORATOR");

        role.addPermission(new Permission("ADD_AIRCRAFT_TO_FLEET"));
        role.addPermission(new Permission("LIST_FLEET"));
        role.addPermission(new Permission("DECOMMISSION_AIRCRAFT"));
        role.addPermission(new Permission("CREATE_FLIGHT_ROUTE"));
        role.addPermission(new Permission("DEACTIVATE_FLIGHT_ROUTE"));
        role.addPermission(new Permission("ADD_PILOT"));
        role.addPermission(new Permission("LIST_PILOT_ROSTER"));
        role.addPermission(new Permission("REMOVE_PILOT"));

        saveRoleIfMissing(role);
    }

    private void saveRoleIfMissing(Role role) {
        if (!roleRepository.existsByName(role.name())) {
            roleRepository.save(role);
        }
    }

    private void createDefaultAdministrator() {
        Email adminEmail = new Email("admin@alsafe.pt");

        if (userRepository.existsByEmail(adminEmail)) {
            return;
        }

        Role administratorRole = roleRepository.findByName("ADMINISTRATOR")
                .orElseThrow(() -> new IllegalStateException("Administrator role was not initialized."));

        User administrator = new User(
                adminEmail,
                "System Administrator",
                new PhoneNumber("+351912345678"),
                new Credential("Password123"),
                Set.of(administratorRole),
                new SecurityClearance(LocalDate.now().plusYears(5)),
                new SkillsAssessment(LocalDate.now(), 12)
        );

        userRepository.save(administrator);
    }
}