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
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationColumnMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationIdMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationManyToOneMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationTableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

public class InsertQueryCreatorTest {
    TableMapper mapper ;

    @Before
    public void setUp() throws Exception {
        ORMFactory factory = new AnnotationORMFactory();
        mapper = factory.getMapper();


    }

    @Test
    public void toSQLString() throws Exception {
        String expected = "INSERT INTO Pirson ( PirsonID,firstname,Name,CUDO_AGE,date) VALUES (12,'Mati','xd',20,'2017-02-09');";
        DBTableObject table = mapper.getTable(Pirson.class);
        Pirson p = new Pirson();
        QueryCreator queryCreator = new InsertQueryCreator(table);
        DBQuery query = queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());

    }

    @Test
    public void toSQLStringPerson() throws Exception {
        Person p = new Person();
        String expected = "INSERT INTO Person ( name,lastname,age,birth_date) VALUES ('Mati','xd',20,'2017-02-09');";
        DBTableObject table = mapper.getTable(Person.class);

        QueryCreator queryCreator = new InsertQueryCreator(table);
        DBQuery query = queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());

    }

    @Test
    public void toSQLStringSuperPerson() throws Exception {
        SuperPerson p = new SuperPerson();
        p.setSuper(1);
        String expected = "INSERT INTO SuperPerson ( spId,lastname,age,birth_date,name,isSuper) VALUES (null,'xd',20,'2017-02-09','Mati',1);";
        DBTableObject table = mapper.getTable(SuperPerson.class);

        QueryCreator queryCreator = new InsertQueryCreator(table);
        DBQuery query = queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());

    }
}