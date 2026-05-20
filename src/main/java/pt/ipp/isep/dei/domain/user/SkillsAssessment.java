package pt.ipp.isep.dei.domain.user;

import java.time.LocalDate;
import java.util.Objects;

public class SkillsAssessment {

    private final LocalDate lastAssessmentDate;
    private final int validityPeriodInMonths;

    public SkillsAssessment(LocalDate lastAssessmentDate, int validityPeriodInMonths) {
        if (lastAssessmentDate == null) {
            throw new IllegalArgumentException("Last assessment date cannot be null.");
        }

        if (validityPeriodInMonths <= 0) {
            throw new IllegalArgumentException("Validity period must be positive.");
        }

        this.lastAssessmentDate = lastAssessmentDate;
        this.validityPeriodInMonths = validityPeriodInMonths;
    }

    public LocalDate lastAssessmentDate() {
        return lastAssessmentDate;
    }

    public int validityPeriodInMonths() {
        return validityPeriodInMonths;
    }

    public LocalDate validUntil() {
        return lastAssessmentDate.plusMonths(validityPeriodInMonths);
    }

    public boolean isValidOn(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        return !validUntil().isBefore(date);
    }

    public boolean isExpiredOn(LocalDate date) {
        return !isValidOn(date);
    }

    @Override
    public String toString() {
        return "SkillsAssessment{" +
                "lastAssessmentDate=" + lastAssessmentDate +
                ", validityPeriodInMonths=" + validityPeriodInMonths +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof SkillsAssessment that)) {
            return false;
        }

        return validityPeriodInMonths == that.validityPeriodInMonths
                && lastAssessmentDate.equals(that.lastAssessmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastAssessmentDate, validityPeriodInMonths);
    }
}