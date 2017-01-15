package pl.edu.agh.wp.orm.mapper.annotation;

import org.junit.Assert;
import org.junit.Test;
import pl.ed.agh.wp.orm.annotations.enums.GenerationType;
import pl.edu.agh.wp.orm.dto.DBIdObject;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.example.Pirson;
import pl.edu.agh.wp.orm.mapper.IdMapper;

import static org.junit.Assert.*;

public class AnnotationIdMapperTest {
    @Test
    public void getIdObjectPerson() throws Exception {
//        IdMapper mapper = new AnnotationIdMapper();
//        DBIdObject obj =mapper.getIdObject(Person.class);
//        Assert.assertEquals("ID",obj.getColumnName());
//        Assert.assertTrue(obj.isAutoGenereted());
//        Assert.assertEquals(GenerationType.AUTO,obj.getGenerationType());
//        Assert.assertEquals("Gen",obj.getSequenceName());
//        Assert.assertNotNull(obj.getConverter());

    }

    @Test
    public void getIdObjectPirzon() throws Exception {
//        IdMapper mapper = new AnnotationIdMapper();
//        DBIdObject obj =mapper.getIdObject(Pirson.class);
//        Assert.assertEquals("PirsonID",obj.getColumnName());
//        Assert.assertFalse(obj.isAutoGenereted());
//        Assert.assertNotNull(obj.getConverter());

    }
}