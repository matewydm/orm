package pl.edu.agh.wp.orm.creator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationColumnMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationTableMapper;

public class InsertQueryCreatorTest {
    TableMapper mapper ;

    @Before
    public void setUp() throws Exception {
        mapper = new AnnotationTableMapper();
        mapper.setColumnMapper(new AnnotationColumnMapper());

    }

    @Test
    public void toSQLString() throws Exception {
        String expected = "INSERT INTO PERSON ( FIRSTNAME,Name,CUDO_AGE,DATE) VALUES (?,?,?,?);";
        DBTableObject table = mapper.getTable(Person.class);
        Person p = new Person();
        QueryCreator queryCreator = new InsertQueryCreator(table);
        DBQuery query =queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());

    }

}