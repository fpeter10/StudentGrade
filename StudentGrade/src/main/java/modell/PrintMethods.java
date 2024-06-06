package modell;

import javafx.collections.ObservableList;

import java.util.List;

public class PrintMethods {

    /**
     * Mentés gomb megnymásakor a szöveg kiíratás
     */
    public static void printSaveGrade(Student student,  Subject subject, double grade, ObservableList<String> resultList){
        System.out.println("Saving test grade: student - " + student.getName() + " subject - "
                + subject.getName() + " grade - " + grade);
        resultList.add("Egy új jegyet hozzáadott " + student.getName() + " tanulóhoz - "
                + subject.getName() + " tárgyból : " + grade);
    }

    /**
     * Törlés gomb megnymásakor a szöveg kiíratás
     */
    public static void printDeleteGrade(Student student,  Subject subject, ObservableList<String> resultList){
        System.out.println("Deleted last grade student: " + student.getName() + " subject - "
                + subject.getName());
        resultList.clear();

        String resultMessage;
        resultMessage = student.getName() + " tanuló utolsó jegyét törölte " + subject.getName()
                + " tárgyból.";
        resultList.add(resultMessage);
    }

    /**
     * Ha nincs mentett jegy szöveg kiíratás
     */
    public static void printNoGrade(Student student,  Subject subject, ObservableList<String> resultList){
        System.out.println("No grade student: " + student.getName() + " subject - "
                + subject.getName());
        resultList.clear();

        String resultMessage;
        resultMessage = student.getName() + " tanulónak nincs jegye " + subject.getName()
                + " tárgyból.";
        resultList.add(resultMessage);
    }

    /**
     * Listázás gomb megnymásakor a szöveg kiíratás
     */
    public static void printListedGrade(Student student,  Subject subject, List<Integer> listGrade, ObservableList<String> resultList){
        resultList.clear();
        if (!(listGrade == null)) {
            System.out.println("Listed grade for student: " + student.getName() + " subject - " + subject.getName());
            resultList.clear();
            String resultMessageList = "A jegyei " + student.getName() +
                    " tanulónak " + subject.getName() + " tárgyból: " + listGrade;
            resultList.add(resultMessageList);
        }
        else {
            String resultMessage = "Nincs rögzített jegy  " + student.getName() + " tanulónak " + subject.getName() + " tárgyból: ";
            resultList.add(resultMessage);
        }
    }

    /**
     * A tantárgy átlagai gomb megnymásakor a szöveg kiíratás
     */
    public static void printAverageClass(Student student,  Subject subject, Double averageGrade, List<Integer> listGrade, ObservableList<String> resultList){
        if (averageGrade != null) {
            String resultMessage = subject.getName() + " tárgyból "
                    + student.getName() + " tanuló átlaga: "
                    + String.format("%.2f", averageGrade);
            resultList.add(resultMessage);
            String resultMessageList = subject.getName() + " tárgyból "
                    + student.getName() + " tanuló jegyei: " + listGrade;
            resultList.add(resultMessageList);
        } else {
            String resultMessage = "Nincs rögzített jegy  " + student.getName() + " tanulónak " + subject.getName() + " tárgyból: ";
            resultList.add(resultMessage);
        }
    }

    /**
     * A tanuló minden átlaga gomb megnymásakor a szöveg kiíratás
     */
    public static void printAverageStudent(Student student,  Subject subject, Double averageGrade, List<Integer> listGrade, ObservableList<String> resultList){
        if (averageGrade != null) {
            String resultMessage = "Az átlaga " + student.getName()
                    + " tanulónak " + subject.getName()
                    + " tárgyból: " + String.format("%.2f", averageGrade);
            resultList.add(resultMessage);
            String resultMessageList = "A jegyei " + student.getName() +
                    " tanulónak " + subject.getName()
                    + "tárgyból: " + listGrade;
            resultList.add(resultMessageList);
        } else {
            String resultMessage = "Nincs rögzített jegy  " + student.getName() + " tanulónak " + subject.getName() + " tárgyból: ";
            resultList.add(resultMessage);
        }
    }


    /**
     * Adott tárgybol az osztály átlag kiíratás
     */
    public static void printClassAverage(double averageGradeClass,  Subject selectedSubject, ObservableList<String> resultList){
        if (averageGradeClass != 0) {
            String sign = "-------------------------";
            resultList.add(sign);
            String resultMessage = selectedSubject.getName()
                    + " tárgyból az osztály átlag: " + String.format("%.2f", averageGradeClass);
            resultList.add(resultMessage);
        }
    }

    /**
     * Gyenge teljesítményű zanuló esetén a szöveg kiíratás
     */
    public static void printBadPerformance(double averageStudent, Student selectedStudent,  Subject selectedSubject, ObservableList<String> resultList, List<Integer> listGrade){
        String resultMessage1 = selectedStudent.getName()
                + " tanulónak " + selectedSubject.getName()
                + " tárgyból az átlaga: " + String.format("%.2f", averageStudent)
                + " gyakoroljon többet vele!";
        resultList.add(resultMessage1);
        String resultMessageList1 = "A jegyei " + selectedStudent.getName() +
                " tanulónak " + selectedSubject.getName()
                + " tárgyból: " + listGrade;
        resultList.add(resultMessageList1);
    }

    /**
     * Kiváló teljesítményü hallgató esetén a szöveg kiíratása
     */
    public static void printExcellentPerformance(double averageStudent, Student selectedStudent,  Subject selectedSubject, ObservableList<String> resultList, List<Integer> listGrade){
        String resultMessage1 = selectedStudent.getName()
                + " tanulónak " + selectedSubject.getName()
                + " tárgyból az átlaga: " + String.format("%.2f", averageStudent)
                + " ami kiváló teljesítmény!";
        resultList.add(resultMessage1);
        String resultMessageList1 = "A jegyei " + selectedStudent.getName() +
                " tanulónak " + selectedSubject.getName()
                + " tárgyból: " + listGrade;
        resultList.add(resultMessageList1);
    }
}
