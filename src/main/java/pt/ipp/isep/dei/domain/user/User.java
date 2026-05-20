package pt.ipp.isep.dei.domain.user;

import pt.ipp.isep.dei.domain.auth.Permission;
import pt.ipp.isep.dei.domain.auth.Role;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private final Email email;
    private final String name;
    private final PhoneNumber phoneNumber;
    private final Credential credential;
    private final Set<Role> roles = new HashSet<>();
    private final SecurityClearance securityClearance;
    private final SkillsAssessment skillsAssessment;

    private UserStatus status;

    public User(
            Email email,
            String name,
            PhoneNumber phoneNumber,
            Credential credential,
            Set<Role> roles,
            SecurityClearance securityClearance,
            SkillsAssessment skillsAssessment
    ) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null.");
        }

        if (credential == null) {
            throw new IllegalArgumentException("Credential cannot be null.");
        }

        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role.");
        }

        if (securityClearance == null) {
            throw new IllegalArgumentException("Security clearance cannot be null.");
        }

        if (skillsAssessment == null) {
            throw new IllegalArgumentException("Skills assessment cannot be null.");
        }

        this.email = email;
        this.name = name.trim();
        this.phoneNumber = phoneNumber;
        this.credential = credential;
        this.roles.addAll(roles);
        this.securityClearance = securityClearance;
        this.skillsAssessment = skillsAssessment;
        this.status = UserStatus.ENABLED;
    }

    public Email email() {
        return email;
    }

    public String name() {
        return name;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public UserStatus status() {
        return status;
    }

    public SecurityClearance securityClearance() {
        return securityClearance;
    }

    public SkillsAssessment skillsAssessment() {
        return skillsAssessment;
    }

    public Set<Role> roles() {
        return Collections.unmodifiableSet(roles);
    }

    public boolean isEnabled() {
        return status.isEnabled();
    }

    public boolean isDisabled() {
        return status.isDisabled();
    }

    public void enable() {
        this.status = UserStatus.ENABLED;
    }

    public void disable() {
        this.status = UserStatus.DISABLED;
    }

    public boolean hasRole(Role role) {
        if (role == null) {
            return false;
        }

        return roles.contains(role);
    }

    public boolean hasPermission(Permission permission) {
        if (permission == null) {
            return false;
        }

        return roles.stream().anyMatch(role -> role.hasPermission(permission));
    }

    public boolean matchesPassword(String rawPassword) {
        return credential.matches(rawPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "email=" + email +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof User user)) {
            return false;
        }

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}