package pl.edu.agh.wp.orm.creator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.example.Pirson;
import pl.edu.agh.wp.orm.example.SuperPerson;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

import java.util.Date;

public class DeleteQueryCreatorTest {

    TableMapper mapper ;

    @Before
    public void setUp() throws Exception {
        ORMFactory factory = new AnnotationORMFactory();
        mapper = factory.getMapper();
    }

    @Test
    public void toSQLStringPerson() throws Exception {
        String expected = "DELETE FROM Person person WHERE person.per_id = 1;";
        DBTableObject table = mapper.getTable(Person.class);
        Person p = new Person();
        p.setId(1);
        QueryCreator queryCreator = new DeleteQueryCreator(table);
        DBQuery query = queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());
    }
}
