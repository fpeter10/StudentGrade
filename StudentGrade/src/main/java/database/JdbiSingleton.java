package database;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 * Adatbázis definiáló osztály
 */
public class JdbiSingleton {
    public static GradeDatabaseDao setupDatabase() {

        var jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        Handle handle = jdbi.open();
        GradeDatabaseDao dao;
        dao = handle.attach(GradeDatabaseDao.class);
        return dao;
    }
}

