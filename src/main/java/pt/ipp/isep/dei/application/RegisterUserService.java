package pt.ipp.isep.dei.application;

import pt.ipp.isep.dei.domain.auth.Role;
import pt.ipp.isep.dei.domain.user.*;
import pt.ipp.isep.dei.repository.RoleRepository;
import pt.ipp.isep.dei.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class RegisterUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterUserService(UserRepository userRepository, RoleRepository roleRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User repository cannot be null.");
        }

        if (roleRepository == null) {
            throw new IllegalArgumentException("Role repository cannot be null.");
        }

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser(RegisterUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Register user request cannot be null.");
        }

        Email email = new Email(request.email());

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

        Set<Role> roles = resolveRoles(request.roleNames());

        User user = new User(
                email,
                request.name(),
                new PhoneNumber(request.phoneNumber()),
                new Credential(request.password()),
                roles,
                new SecurityClearance(request.securityClearanceExpirationDate()),
                new SkillsAssessment(
                        request.skillsAssessmentDate(),
                        request.skillsAssessmentValidityPeriodInMonths()
                )
        );

        return userRepository.save(user);
    }

    private Set<Role> resolveRoles(Set<String> roleNames) {
        if (roleNames == null || roleNames.isEmpty()) {
            throw new IllegalArgumentException("At least one role must be provided.");
        }

        Set<Role> roles = new HashSet<>();

        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new IllegalArgumentException("Role does not exist: " + roleName));

            roles.add(role);
        }

        return roles;
    }
}