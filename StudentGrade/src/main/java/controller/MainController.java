package controller;

import modell.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.*;
import static modell.MethodsDefinition.*;

/**
 * Gombok, és a válaszok  kezelése
 */
public class MainController {

    //Gombok
    @FXML
    private RadioButton studentRadioButton1;
    @FXML
    private RadioButton studentRadioButton2;
    @FXML
    private RadioButton studentRadioButton3;
    @FXML
    private RadioButton studentRadioButton4;
    @FXML
    private RadioButton studentRadioButton5;
    @FXML
    private RadioButton studentRadioButton6;
    @FXML
    private RadioButton studentRadioButton7;
    @FXML
    private RadioButton studentRadioButton8;
    @FXML
    private RadioButton studentRadioButton9;
    @FXML
    private RadioButton studentRadioButton10;
    @FXML
    private RadioButton subjectRadioButton1;
    @FXML
    private RadioButton subjectRadioButton2;
    @FXML
    private RadioButton subjectRadioButton3;
    @FXML
    private RadioButton subjectRadioButton4;
    @FXML
    private RadioButton subjectRadioButton5;
    @FXML
    private TextField gradeTextFieldUI;
    @FXML
    private Button saveGradeButtonUI;
    @FXML
    private Button AverageClassUI;
    @FXML
    private Button testDataUI;
    @FXML
    private Button PerformanceUI;
    @FXML
    private Button AverageStudentSubjectUI;
    @FXML
    private Button DeleteButtonUI1;
    @FXML
    private Button ListGradesUI1;
    @FXML
    private ListView<String> resultTableUI;
    @FXML
    private ObservableList<String> resultList;

    public void initialize() {
        resultList = FXCollections.observableArrayList();
        resultTableUI.setItems(resultList);
        resultTableUI.setCellFactory(listView -> new TextFlowListCell());
    }

    /**
     * Gomb lenyomásával tanuló választása
     */
    private Student getSelectedStudent() {
        if (studentRadioButton1.isSelected()) return new Student(1,"Anna");
        else if (studentRadioButton2.isSelected()) return new Student(2, "Béla");
        else if (studentRadioButton3.isSelected()) return new Student(3, "Csaba");
        else if (studentRadioButton4.isSelected()) return new Student(4, "Dóri");
        else if (studentRadioButton5.isSelected()) return new Student(5, "Ernő");
        else if (studentRadioButton6.isSelected()) return new Student(6, "Ferenc");
        else if (studentRadioButton7.isSelected()) return new Student(7, "György");
        else if (studentRadioButton8.isSelected()) return new Student(8, "Hanna");
        else if (studentRadioButton9.isSelected()) return new Student(9, "Ilona");
        else if (studentRadioButton10.isSelected()) return new Student(10, "Kati");
        return null;
    }

    /**
     * Gomb lenyomásával tantárgy választása
     */
    private Subject getSelectedSubject() {
        if (subjectRadioButton1.isSelected()) return new Subject( "Matematika");
        else if (subjectRadioButton2.isSelected()) return new Subject( "Magyar irodalom");
        else if (subjectRadioButton3.isSelected()) return new Subject( "Történelem");
        else if (subjectRadioButton4.isSelected()) return new Subject( "Angol");
        else if (subjectRadioButton5.isSelected()) return new Subject( "Informatika");
        return null;
    }

    //Adat bevitel

    /**
     * Mentés gomb lenyomásakor mit csináljon
     */
    @FXML
    public void handleSaveGrade() {
        if (saveGradeButtonUI != null && saveGradeButtonUI.isArmed()) {
            Student selectedStudent = getSelectedStudent();
            Subject selectedSubject = getSelectedSubject();

            List<StudentGrade> gradeRecords = saveGrades(selectedStudent, selectedSubject, gradeTextFieldUI);

            for (StudentGrade record : gradeRecords) {
                Student student = record.getStudent();
                Subject subject = record.getSubject();
                int grade = record.getGrade();
                DatabaseDefinitions.databaseInsert(student, subject, grade, resultList);
            }
        }
    }

    /**
     * Teszt gomb lenyomásakor mit csináljon
     */
    @FXML
    public void handleGenerateTestData() {
        if (testDataUI != null && testDataUI.isArmed()) {
            System.out.println("Teszt adatok generálás");
            List<StudentGrade> gradeRecords = generateRandomGrades();
            for (StudentGrade record : gradeRecords) {
                Student student = record.getStudent();
                Subject subject = record.getSubject();
                int grade = record.getGrade();
                DatabaseDefinitions.databaseInsert(student, subject, grade, resultList);
            }
        }
    }

    /**
     * Törlés gomb lenyomásakor mit csináljon
     */
    @FXML
    private void handleDelete() {
        Student selectedStudent = getSelectedStudent();
        Subject selectedSubject = getSelectedSubject();

        if (DeleteButtonUI1 != null && DeleteButtonUI1.isArmed()) {
            assert selectedStudent != null;
            assert selectedSubject != null;
            DatabaseDefinitions.databaseDelete(selectedStudent,  selectedSubject, resultList);
        }
    }

// Lekérdezések

    /**
     * Listázás gomb lenyomásakor mit csináljon
     */
    public void HandleListGrades() {
        if ((ListGradesUI1 != null) && ListGradesUI1.isArmed()) {
            Student selectedStudent = getSelectedStudent();
            Subject selectedSubject = getSelectedSubject();
            assert selectedStudent != null;
            assert selectedSubject != null;
            DatabaseDefinitions.databaseList(selectedStudent, selectedSubject, resultList);
        }
    }

    /**
     * A tantárgy átlagai gomb lenyomásakor mit csináljon
     */
    public void handleAverageClass() {
        if ((AverageClassUI != null) && AverageClassUI.isArmed()) {
            resultList.clear();
            Subject selectedSubject = getSelectedSubject();
            DatabaseDefinitions.databaseAverageClass(selectedSubject, resultList);
        }
    }

    /**
     *  Atanuló átlagai gomb lenyomásakor mit csináljon
     */
    public void handleCalculateAverageSubject() {
        if ((AverageStudentSubjectUI != null) && AverageStudentSubjectUI.isArmed()) {
            Student selectedStudent = getSelectedStudent();
            resultList.clear();
            DatabaseDefinitions.databaseAverageStudent(selectedStudent, resultList);
        }
    }

    /**
     * Teljesítmény gomb lenyomásakor mit csináljon
     */
    public void handlePerformance() {
        if ((PerformanceUI != null) && PerformanceUI.isArmed()) {
            resultList.clear();
            DatabaseDefinitions.databasePerformance(resultList);
            }
        }
}
