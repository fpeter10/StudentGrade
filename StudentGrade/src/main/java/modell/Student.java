package modell;

import lombok.Getter;
import lombok.Setter;

/**
 * Tanuló osztály
 */
@Setter
@Getter
public class Student {
    private int id;
    private final String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
