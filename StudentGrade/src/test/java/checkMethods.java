import modell.MethodsDefinition;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class checkMethods {

    @Test
    public void testValidGrade() {
        Optional<Integer> result = MethodsDefinition.GradeChecker.checkGrade("3");
        assertTrue(result.isPresent());
        assertEquals(3, result.get());
    }

    @Test
    public void testInvalidGradeAboveRange() {
        Optional<Integer> result = MethodsDefinition.GradeChecker.checkGrade("6");
        assertFalse(result.isPresent());
    }

    @Test
    public void testInvalidGradeBelowRange() {
        Optional<Integer> result = MethodsDefinition.GradeChecker.checkGrade("0");
        assertFalse(result.isPresent());
    }

    @Test
    public void testNonNumericGrade() {
        Optional<Integer> result = MethodsDefinition.GradeChecker.checkGrade("abc");
        assertFalse(result.isPresent());
    }

    @Test
    public void testBadStudentAccepted() {
       boolean result =  MethodsDefinition.isBadStudent(1.23, 3.65);
       assertTrue(result);
    }

    @Test
    public void testBadStudentNotAccepted() {
        boolean result =  MethodsDefinition.isBadStudent(4.23, 3.45);
        assertFalse(result);
    }

    @Test
    public void testBadStudentCloseToAverage() {
        boolean result =  MethodsDefinition.isBadStudent(2.11, 2.45);
        assertFalse(result);
    }

    @Test
    public void testExcellentStudentAccepted() {
        boolean result =  MethodsDefinition.isExcellentStudent(4.67);
        assertTrue(result);
    }

    @Test
    public void testExcellenttudentNotAccepted() {
        boolean result =  MethodsDefinition.isExcellentStudent(3.98);
        assertFalse(result);
    }
}
