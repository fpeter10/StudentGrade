package modell;

import database.GradeDatabaseDao;
import javafx.collections.ObservableList;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

/**
 * Adatbázis műveletek kezelése
 */
public class DatabaseDefinitions {
    private static final Jdbi jdbi;
    private static final GradeDatabaseDao dao;

    static  {
        // Initialize Jdbi and DAO
        jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        dao = jdbi.onDemand(GradeDatabaseDao.class);
        dao.createTable(); // Ensure the table is created
    }

    /**
     * Beszúrás adatbázisba
     */
    public static void databaseInsert(Student student, Subject subject, int grade, ObservableList<String> resultList){
        dao.insert(student.getName(), subject.getName(), grade);
        PrintMethods.printSaveGrade(student, subject, grade, resultList);
    }

    /**
     * Törlés adatbázisbol
     */
    public static void databaseDelete(Student selectedStudent, Subject selectedSubject, ObservableList<String> resultList) {
        int gradeCount = dao.countGradesForStudentAndSubject(selectedStudent.getName(), selectedSubject.getName());
        if (gradeCount > 0) {
            int latestGradeId = dao.findLatestGradeId(selectedStudent.getName(), selectedSubject.getName());
            dao.deleteGradeById(latestGradeId);
            PrintMethods.printDeleteGrade(selectedStudent, selectedSubject, resultList);
        }
        else {
            PrintMethods.printNoGrade(selectedStudent, selectedSubject, resultList);
        }
    }

    /**
     * Jegyek kiliztászása
     */
    public static void databaseList(Student selectedStudent, Subject selectedSubject, ObservableList<String> resultList) {
        int gradeCount = dao.countGradesForStudentAndSubject(selectedStudent.getName(), selectedSubject.getName());
        if (gradeCount > 0) {
            List<Integer> listGrade = dao.listGradesFromSubject(selectedStudent.getName(), selectedSubject.getName());
            PrintMethods.printListedGrade(selectedStudent, selectedSubject, listGrade, resultList);
        }
        else {
            PrintMethods.printNoGrade(selectedStudent, selectedSubject, resultList);
        }
    }

    /**
     * Adatbázisbol lekérdezés egy tárgy átlagait
     */
    public static void databaseAverageClass(Subject selectedSubject, ObservableList<String> resultList) {
        List<Student> studentList =  MethodsDefinition.StudentListProvider.studentList;
        resultList.clear();
        for (Student selectedStudent : studentList) {
            assert selectedSubject != null;
            Double averageGrade = dao.getAverageGradeSubject(selectedStudent.getName(), selectedSubject.getName());
            List<Integer> listGrade = dao.listGradesFromSubject(selectedStudent.getName(), selectedSubject.getName());
            PrintMethods.printAverageClass(selectedStudent, selectedSubject, averageGrade, listGrade, resultList);
        }
    }

    /**
     * Adatbázisbol egy tanulo minden eredményének lekérdezése
     */
    public static void databaseAverageStudent(Student selectedStudent, ObservableList<String> resultList) {
        List<Subject> subjectList =  MethodsDefinition.SubjectListProvider.subjectList;
        resultList.clear();
        for (Subject selectedSubject : subjectList) {
            assert selectedStudent != null;
            Double averageGrade = dao.getAverageGradeSubject(selectedStudent.getName(), selectedSubject.getName());
            List<Integer> listGrade = dao.listGradesFromSubject(selectedStudent.getName(), selectedSubject.getName());
            PrintMethods.printAverageStudent(selectedStudent, selectedSubject, averageGrade, listGrade, resultList);
        }
    }

    /**
     * Gyenge és kiváló tanuló lekérdezése
     */
    public static void databasePerformance(ObservableList<String> resultList) {
        List<Student> studentList =  MethodsDefinition.StudentListProvider.studentList;

        for (Subject selectedSubject : MethodsDefinition.SubjectListProvider.subjectList) {
            String subjectName = selectedSubject.getName();
            if (dao.countGrades(subjectName) >= 30) {
                Double averageGradeClass = dao.getSubjectAverageGrade(selectedSubject.getName());
                PrintMethods.printClassAverage(averageGradeClass ,selectedSubject,resultList);

                for (Student selectedStudent : studentList) {
                    Double averageStudent = dao.getAverageGradeSubject(selectedStudent.getName(), selectedSubject.getName());
                    if(MethodsDefinition.isBadStudent(averageStudent, averageGradeClass)){
                       List<Integer> listGradeBadStudent = dao.listGradesFromSubject(selectedStudent.getName(), selectedSubject.getName());
                        PrintMethods.printBadPerformance(averageStudent, selectedStudent, selectedSubject, resultList, listGradeBadStudent);

                    } else if (MethodsDefinition.isExcellentStudent(averageStudent)) {
                        List<Integer> listGradeExcelletStudent = dao.listGradesFromSubject(selectedStudent.getName(), selectedSubject.getName());
                        PrintMethods.printExcellentPerformance(averageStudent, selectedStudent, selectedSubject, resultList, listGradeExcelletStudent);
                    }
                }
            } else {
                String resultMessage2 = "Nincs elég rögzített jegy  " + selectedSubject.getName() + " tárgyból: ";
                resultList.add(resultMessage2);
            }
        }
    }
}

