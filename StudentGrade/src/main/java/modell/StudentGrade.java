package modell;

import lombok.Getter;

/**
 * Jegy osztály
 */
@Getter
public class StudentGrade {
    private final Student student;
    private final Subject subject;
    private final int grade;

    public StudentGrade(Student student, Subject subject, int grade) {
        this.student = student;
        this.subject = subject;
        this.grade = grade;
    }
}
