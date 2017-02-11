package pl.edu.agh.wp.orm.creator;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.example.PhoneNumber;
import pl.edu.agh.wp.orm.example.Pirson;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

public class CreateQueryCreatorTest {
    TableMapper mapper ;

    @Before
    public void setUp() throws Exception {
        ORMFactory factory = new AnnotationORMFactory();
        mapper = factory.getMapper();
    }

    @Test
    public void toSQLString() throws Exception {
        String expected = "CREATE TABLE Pirson (PirsonID INTEGER, CUDO_AGE INTEGER, Name VARCHAR(100), firstname VARCHAR(100), date DATE);";
        DBTableObject table = mapper.getTable(Pirson.class);
        QueryCreator queryCreator = new CreateQueryCreator(table);
        DBQuery query = queryCreator.createQuery(null);
        Assert.assertEquals(expected, query.getSQLQuery());
    }

    @Test
    public void toSQLStringPerson() throws Exception {
        String expected = "CREATE TABLE Person (per_id INTEGER, lastname VARCHAR(50), age INTEGER, birth_date DATE, name VARCHAR(50));";
        DBTableObject table = mapper.getTable(Person.class);
        QueryCreator queryCreator = new CreateQueryCreator(table);
        DBQuery query = queryCreator.createQuery(null);
        Assert.assertEquals(expected, query.getSQLQuery());
    }

    @Test
    public void toSQLStringPhone() throws Exception {
        String expected = "CREATE TABLE PhoneNumber (phone_id INTEGER, model VARCHAR(50), version VARCHAR(50), address_ID INTEGER, person_ID INTEGER);";
        DBTableObject table = mapper.getTable(PhoneNumber.class);
        QueryCreator queryCreator = new CreateQueryCreator(table);
        DBQuery query = queryCreator.createQuery(null);
        query.getSQLQuery();
        Assert.assertEquals(expected, query.getSQLQuery());
        }
}