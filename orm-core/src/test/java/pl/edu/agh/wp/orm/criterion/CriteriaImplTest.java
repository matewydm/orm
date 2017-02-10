package pl.edu.agh.wp.orm.criterion;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.session.Session;
import pl.edu.agh.wp.orm.session.SessionFactory;

import java.util.List;

/**
 * Created by mucha on 09.02.2017.
 */
public class CriteriaImplTest {

    SessionFactory factory;

    @Before
    public void setUp() throws Exception {
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        factory = configuration.buildSessionFactory();
    }

    @Test
    public void execute() throws Exception {
        String sqlString = "INSERT INTO person (name,lastname,age,birth_date) VALUES ('abba','a',20,'2017-02-09');";
        Session session = factory.openSession();

        List person = session.createCriteria(Person.class)
                .add( Restrictions.and(
                        Restrictions.like("name", "%"),
                        Restrictions.between("age",0,20)) )
                .add( Restrictions.like("lastname","%"))
                .list();
    }
}