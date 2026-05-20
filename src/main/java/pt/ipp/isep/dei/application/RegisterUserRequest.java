package pt.ipp.isep.dei.application;

import java.time.LocalDate;
import java.util.Set;

public class RegisterUserRequest {

    private final String email;
    private final String name;
    private final String phoneNumber;
    private final String password;
    private final Set<String> roleNames;
    private final LocalDate securityClearanceExpirationDate;
    private final LocalDate skillsAssessmentDate;
    private final int skillsAssessmentValidityPeriodInMonths;

    public RegisterUserRequest(
            String email,
            String name,
            String phoneNumber,
            String password,
            Set<String> roleNames,
            LocalDate securityClearanceExpirationDate,
            LocalDate skillsAssessmentDate,
            int skillsAssessmentValidityPeriodInMonths
    ) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleNames = roleNames;
        this.securityClearanceExpirationDate = securityClearanceExpirationDate;
        this.skillsAssessmentDate = skillsAssessmentDate;
        this.skillsAssessmentValidityPeriodInMonths = skillsAssessmentValidityPeriodInMonths;
    }

    public String email() {
        return email;
    }

    public String name() {
        return name;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String password() {
        return password;
    }

    public Set<String> roleNames() {
        return roleNames;
    }

    public LocalDate securityClearanceExpirationDate() {
        return securityClearanceExpirationDate;
    }

    public LocalDate skillsAssessmentDate() {
        return skillsAssessmentDate;
    }

    public int skillsAssessmentValidityPeriodInMonths() {
        return skillsAssessmentValidityPeriodInMonths;
    }
}