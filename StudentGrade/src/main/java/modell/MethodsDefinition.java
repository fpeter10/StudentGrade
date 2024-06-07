package modell;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.util.*;

public class MethodsDefinition {

    /**
     * Választható tantárgyak
     */
    public static class SubjectListProvider {
        public static final List<Subject> subjectList = Arrays.asList(
                new Subject( "Matematika"),
                new Subject( "Magyar irodalom"),
                new Subject( "Történelem"),
                new Subject( "Angol"),
                new Subject( "Informatika")
        );
    }

    /**
     * Választható tanulók
     */
    public static class StudentListProvider {
        public static final List<Student> studentList = Arrays.asList(
                new Student(1, "Anna"),
                new Student(2, "Béla"),
                new Student(3, "Csaba"),
                new Student(4, "Dóri"),
                new Student(5, "Ernő"),
                new Student(6, "Ferenc"),
                new Student(7, "György"),
                new Student(8, "Hanna"),
                new Student(9, "Ilona"),
                new Student(10, "Kati")
        );
    }

    /**
     * Felugró figyelmeztetés
     */
    public static class ShowAlert {
        public static void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }

    /**
     * A jegy ellenörzése hogy megfelelő e?
     */
    public static class GradeChecker {
        public static Optional<Integer> checkGrade(String gradeText) {
            try {
                int grade = Integer.parseInt(gradeText);
                if (grade >= 1 && grade <= 5) {
                    return Optional.of(grade);
                } else {
                    return Optional.empty();
                }
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
    }

    /**
     * Jegy bevitel
     */
    public static class GradeUIHandler {
        public static Optional<Integer> handleGradeInput(TextField gradeTextFieldUI) {
            Optional<Integer> grade = GradeChecker.checkGrade(gradeTextFieldUI.getText());
            if (grade.isPresent()) {
                return grade;
            } else {
                ShowAlert.showAlert("Hiba", "Nem megfelelő jegy, próbáld újra!");
                return Optional.empty();
            }
        }
    }

    /**
     * Random jegyek generálása
     */
    public static List<StudentGrade> generateRandomGrades() {
        List<Student> studentList = StudentListProvider.studentList;
        List<Subject> subjectList = SubjectListProvider.subjectList;

        List<StudentGrade> gradeRecords = new ArrayList<>();
        Random random = new Random();
        for (Student student : studentList) {
            for (Subject subject : subjectList) {
                int grade = random.nextInt(5) + 1; // Random grade between 1 and 5
                gradeRecords.add(new StudentGrade(student, subject, grade));
            }
        }
        return gradeRecords;
    }


    /**
     * Jegy mentése
     */
    public static List<StudentGrade> saveGrades(Student studentChoose, Subject subjectChoose, TextField inputUI) {
        List<StudentGrade> gradeRecords = new ArrayList<>();
        Optional<Integer> optionalGrade = GradeUIHandler.handleGradeInput(inputUI);
        if (optionalGrade.isPresent()) {
            int grade = optionalGrade.get();
            assert studentChoose != null;
            assert subjectChoose != null;
            gradeRecords.add(new StudentGrade(studentChoose, subjectChoose, grade));
        }
        return gradeRecords;
    }

    /**
     * Gyenge tanuló e?
     */
    public static boolean isBadStudent(double averageStudent, double averageGradeClass) {
        return averageStudent < 2.3 || (averageGradeClass - averageStudent) > 1.5;
    }

    /**
     * Kiváló tanuló e?
     */
    public static boolean isExcellentStudent(double averageStudent) {
        return averageStudent > 4.5;
    }
}



