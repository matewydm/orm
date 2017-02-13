package pl.edu.agh.wp.orm.session;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.example.NonExistent;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.exception.ORMNoSuchRecordException;
import pl.edu.agh.wp.orm.exception.ORMNoSuchTableException;

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
        person.setId(1000);
        session.save(person);
        session.delete(person);
    }


    @Test(expected = ORMNoSuchRecordException.class)
    public void throwExceptionWhenIdIsWrong() throws ORMNoSuchRecordException {
        Person person = new Person();
        person.setId(-1);
        session.delete(person);

    }

    @Test(expected = ORMNoSuchTableException.class)
    public void throwExceptionWhenClassHasNoDBTableAnnotation() throws ORMNoSuchTableException {
        NonExistent nonExistent = new NonExistent();
        session.delete(nonExistent);
    }
}
