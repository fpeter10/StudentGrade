package database;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface GradeDatabaseDao{

    @SqlUpdate("""
    CREATE TABLE gradedatabase (
        id INTEGER AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR NOT NULL,
        subject VARCHAR NOT NULL,
        grade INTEGER NOT NULL
    )
""")
    void createTable();

    /**
     * Jegy mentése adatbázisba
     */
    @SqlUpdate("INSERT INTO gradedatabase (name, subject, grade) VALUES (:name, :subject, :grade)")
    @GetGeneratedKeys("id")
    int insert(@Bind("name") String name, @Bind("subject") String subject, @Bind("grade") int grade);

    /**
     * Osztály átlaga
     */
    @SqlQuery("SELECT AVG(grade) FROM gradedatabase WHERE subject = :subject")
    Double getSubjectAverageGrade(@Bind("subject") String subject);

    /**
     * A kiválasztott tanuló átlaga
     */
    @SqlQuery("SELECT AVG(grade) FROM gradedatabase WHERE name = :name AND subject = :subject")
    Double getAverageGradeSubject(@Bind("name") String name, @Bind("subject") String subject);

    /**
     * Az tantárgy eredményeinek kilistázása
     */
    @SqlQuery("SELECT grade FROM gradedatabase WHERE name = :name AND subject = :subject")
    List<Integer> listGradesFromSubject(@Bind("name") String name, @Bind("subject") String subject);

    /**
     * Törlés adatbázisból
     */
    @SqlUpdate("DELETE FROM gradedatabase WHERE id = :id")
    void deleteGradeById(@Bind("id") int id);

    /**
     * Legutolsó bevitt adat lekérdezése adott tanuló adott tárgyából
     */
    @SqlQuery("SELECT id FROM gradedatabase WHERE name = :name AND subject = :subject ORDER BY id DESC")
    Integer findLatestGradeId(@Bind("name") String name, @Bind("subject") String subject);

    /**
     * A tárgyból hány jegy van rögzítve
     */
    @SqlQuery("SELECT COUNT(*) FROM gradedatabase WHERE subject = :subject")
    Integer countGrades(@Bind("subject") String subject);

    /**
     * Adott tanulónak hány jegye van rögzítve
     */
    @SqlQuery("SELECT COUNT(*) FROM gradedatabase WHERE name = :name AND subject = :subject")
    int countGradesForStudentAndSubject(@Bind("name") String name, @Bind("subject") String subject);

}

