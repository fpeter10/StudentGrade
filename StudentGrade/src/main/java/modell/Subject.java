package modell;

import lombok.Getter;

/**
 * Tantárgy osztály
 */
@Getter
public class Subject {
    private final String name;

    public Subject(String name) {
        this.name = name;
    }
}
