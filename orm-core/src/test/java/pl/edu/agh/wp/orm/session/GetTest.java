package pl.edu.agh.wp.orm.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.example.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mucha on 12.02.2017.
 */
public class GetTest {

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
    public void getTest1() throws Exception {
        Object expectedId = 1;
        Person person = (Person) session.get(expectedId,Person.class);
        Assert.assertEquals(person.getId(),expectedId);
    }
}
