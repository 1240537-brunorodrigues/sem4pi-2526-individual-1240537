package pt.ipp.isep.dei.domain.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SkillsAssessmentTest {

    @Test
    void shouldCreateSkillsAssessment() {
        LocalDate assessmentDate = LocalDate.of(2026, 1, 1);

        SkillsAssessment assessment = new SkillsAssessment(assessmentDate, 12);

        assertEquals(assessmentDate, assessment.lastAssessmentDate());
        assertEquals(12, assessment.validityPeriodInMonths());
    }

    @Test
    void shouldRejectNullLastAssessmentDate() {
        assertThrows(IllegalArgumentException.class, () -> new SkillsAssessment(null, 12));
    }

    @Test
    void shouldRejectInvalidValidityPeriod() {
        assertThrows(IllegalArgumentException.class,
                () -> new SkillsAssessment(LocalDate.of(2026, 1, 1), 0));
    }

    @Test
    void shouldCalculateValidUntilDate() {
        SkillsAssessment assessment = new SkillsAssessment(LocalDate.of(2026, 1, 1), 12);

        assertEquals(LocalDate.of(2027, 1, 1), assessment.validUntil());
    }

    @Test
    void shouldBeValidBeforeExpiration() {
        SkillsAssessment assessment = new SkillsAssessment(LocalDate.of(2026, 1, 1), 12);

        assertTrue(assessment.isValidOn(LocalDate.of(2026, 12, 31)));
    }

    @Test
    void shouldBeValidOnExpiration() {
        SkillsAssessment assessment = new SkillsAssessment(LocalDate.of(2026, 1, 1), 12);

        assertTrue(assessment.isValidOn(LocalDate.of(2027, 1, 1)));
    }

    @Test
    void shouldBeExpiredAfterExpiration() {
        SkillsAssessment assessment = new SkillsAssessment(LocalDate.of(2026, 1, 1), 12);

        assertTrue(assessment.isExpiredOn(LocalDate.of(2027, 1, 2)));
    }

    @Test
    void equalSkillsAssessmentsShouldBeEqual() {
        SkillsAssessment assessment1 = new SkillsAssessment(LocalDate.of(2026, 1, 1), 12);
        SkillsAssessment assessment2 = new SkillsAssessment(LocalDate.of(2026, 1, 1), 12);

        assertEquals(assessment1, assessment2);
        assertEquals(assessment1.hashCode(), assessment2.hashCode());
    }
}