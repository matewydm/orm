package pl.edu.agh.wp.orm.creator;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

import java.util.Date;

public class UpdateQueryCreatorTest {
    TableMapper mapper ;
    String date;

    @Before
    public void setUp() throws Exception {
        ORMFactory factory = new AnnotationORMFactory();
        mapper = factory.getMapper();
        date = new java.sql.Date((new Date()).getTime()).toString();
    }

    @Test
    public void toSQLStringPerson() throws Exception {
        String expected = "UPDATE Person SET lastname = 'xd', age = 20, birth_date = " +"\'" + date + "\'" + ", name = 'Mati' WHERE per_id = 1;";
        DBTableObject table = mapper.getTable(Person.class);
        Person p = new Person();
        p.setId(1);
        QueryCreator queryCreator = new UpdateQueryCreator(table);
        DBQuery query = queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());
    }
}
