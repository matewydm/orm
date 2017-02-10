package pl.edu.agh.wp.orm.session;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.example.NonExistent;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeleteTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        session = configuration.buildSessionFactory().openSession();
    }

    @Test
    public void deleteTest() throws Exception {
        Person person = new Person();
        person.setId(16);
        session.delete(person);
    }

    @Test
    public void throwExceptionWhenIdIsWrong() throws Exception {
        try {
            Person person = new Person();
            person.setId(-1);
            session.delete(person);
        } catch (final ORMException e) {
            final String msg = "Object id was not found";
            assertEquals(msg,e.getMessage());
        }
    }

    @Test
    public void throwExceptionWhenClassHasNoDBTableAnnotation() throws Exception {
        try {
            NonExistent nonExistent = new NonExistent();
            session.delete(nonExistent);
        } catch (final ORMException e) {
            final String msg = "Given class has no table in repository";
            assertEquals(msg,e.getMessage());
        }
    }
}
