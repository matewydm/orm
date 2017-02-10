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

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertQueryCreatorTest {
    TableMapper mapper ;
    String date;

    @Before
    public void setUp() throws Exception {
        ORMFactory factory = new AnnotationORMFactory();
        mapper = factory.getMapper();
        date = new java.sql.Date((new Date()).getTime()).toString();

    }

    @Test
    public void toSQLString() throws Exception {
        String expected = "INSERT INTO Pirson ( PirsonID,CUDO_AGE,Name,firstname,date) VALUES (12,20,'xd','Mati',"+ date +");";
        DBTableObject table = mapper.getTable(Pirson.class);
        Pirson p = new Pirson();
        QueryCreator queryCreator = new InsertQueryCreator(table);
        DBQuery query = queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());
    }

    @Test
    public void toSQLStringPerson() throws Exception {
        Person p = new Person();
        String expected = "INSERT INTO Person ( lastname,age,birth_date,name) VALUES ('xd',20,"+date+",'Mati');";
        DBTableObject table = mapper.getTable(Person.class);

        QueryCreator queryCreator = new InsertQueryCreator(table);
        DBQuery query = queryCreator.createQuery(p);
        Assert.assertEquals(expected,query.getSQLQuery());

    }
//
//    @Test
//    public void toSQLStringSuperPerson() throws Exception {
//
//        SuperPerson p = new SuperPerson();
//        p.setSuper(1);
//
//        String expected = "INSERT INTO SuperPerson ( spId,lastname,age,birth_date,isSuper,name) VALUES (null,'xd',20," + date + ",1,'Mati');";
//        DBTableObject table = mapper.getTable(SuperPerson.class);
//
//        QueryCreator queryCreator = new InsertQueryCreator(table);
//        DBQuery query = queryCreator.createQuery(p);
//        String actual = query.getSQLQuery();
//        Assert.assertEquals(expected,actual);
//    }
}