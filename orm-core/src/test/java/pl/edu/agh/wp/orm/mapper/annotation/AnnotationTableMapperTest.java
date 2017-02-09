package pl.edu.agh.wp.orm.mapper.annotation;

import org.junit.Assert;
import org.junit.Test;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.example.SuperPerson;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class AnnotationTableMapperTest {

    @Test
    public void getTable() throws Exception {
        ORMFactory factory = new AnnotationORMFactory();
        TableMapper mapper = factory.getMapper();

        DBTableObject table = mapper.getTable(Person.class);

    }

    @Test
    public void getTableInherited() throws Exception {
        ORMFactory factory = new AnnotationORMFactory();
        TableMapper mapper = factory.getMapper();

        DBTableObject table = mapper.getTable(SuperPerson.class);



    }

    @Test
    public void tesFieldSupport() throws Exception {
        Field field = Person.class.getDeclaredField("firstname");
        Field field1 = Person.class.getDeclaredField("transients");
        Assert.assertTrue(AnnotationTableMapper.isFieldSuported(field));
        Assert.assertFalse(AnnotationTableMapper.isFieldSuported(field1));

    }
}