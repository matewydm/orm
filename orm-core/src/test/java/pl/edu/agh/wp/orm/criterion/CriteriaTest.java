package pl.edu.agh.wp.orm.criterion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.queries.SelectQuery;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationColumnMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationTableMapper;
import pl.edu.agh.wp.orm.session.DefaultSession;
import pl.edu.agh.wp.orm.session.Session;

import java.util.List;

public class CriteriaTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        Configuration configuration = new Configuration()
                .addProperties("driver_class","org.postgresql.Driver")
                .addProperties("db_url","jdbc:postgresql://localhost:5432/postgres")
                .addProperties("user","postgres")
                .addProperties("password","dare");

        session = configuration.buildSessionFactory().openSession();
    }

    @Test
    public void buildCriteriaTest() {

        String expected = "SELECT * FROM PERSON person WHERE person.name LIKE '%'  AND person.age BETWEEN 4 AND 3 ";
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
