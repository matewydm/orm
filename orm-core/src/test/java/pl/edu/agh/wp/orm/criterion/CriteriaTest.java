package pl.edu.agh.wp.orm.criterion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.session.Session;

import java.util.List;

public class CriteriaTest {

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
    public void buildConjunctionCriteriaTest() {

        String expected = "SELECT * FROM Person person WHERE person.name LIKE '%'  AND age BETWEEN 4 AND 3  AND person.lastname LIKE 'd%'  ";
        DBQuery query = session.createCriteria(Person.class)
                .add( Restrictions.and(
                        Restrictions.like("name", "%"),
                        Restrictions.between("age",4,3)) )
                .add( Restrictions.like("lastname","d%"))
                .build();

        Assert.assertEquals(expected,query.getSQLQuery());

    }


    @Test
    public void buildNullCriteriaTest() {

        String expected = "SELECT * FROM Person person WHERE person.name IS NULL  ";
        DBQuery query = session.createCriteria(Person.class)
                .add( Restrictions.isNull("name"))
                .build();

        Assert.assertEquals(expected,query.getSQLQuery());

    }

    @Test
    public void buildInCriteriaTest() {

        Object[] array = {"dare", "daa"};
        String expected = "SELECT * FROM Person person WHERE person.name IN ('dare','daa') ";
        DBQuery query = session.createCriteria(Person.class)
                .add( Restrictions.in("name", array))
                .build();

        Assert.assertEquals(expected,query.getSQLQuery());

    }

    @Test
    public void buildCriteriaTest() {

        String expected = "SELECT * FROM Person person WHERE person.name LIKE '%'  AND person.age BETWEEN 4 AND 3 ";
        DBQuery query = session.createCriteria(Person.class)
                .add( Restrictions.like("name", "%") )
                .add( Restrictions.between("age",4,3))
                .build();

        Assert.assertEquals(expected,query.getSQLQuery());

    }

    @Test
    @SuppressWarnings("unchecked")
    public void listTest(){
        List<Person> personList = (List<Person>) session.createCriteria(Person.class)
                .add( Restrictions.like("name", "%") )
                .add( Restrictions.between("age",0,3))
                .list();

        Assert.assertTrue(personList.size() == 5);

    }
}
