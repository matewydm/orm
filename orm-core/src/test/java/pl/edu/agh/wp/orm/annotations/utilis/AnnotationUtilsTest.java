package pl.edu.agh.wp.orm.annotations.utilis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.edu.agh.wp.orm.example.Person;

import java.lang.reflect.Field;

public class AnnotationUtilsTest {

    Person person;
    @Before
    public void prepareObject(){
        person = new Person();
    }

    @Test
    public void preparePropertyNameAnnotation() throws Exception {
        String propertyName = "test";
        String fieldName = "TTT";
        Assert.assertEquals("Not the same", propertyName, AnnotationUtils.preparePropertyName(propertyName, fieldName));
    }
    @Test
    public void preparePropertyNameField() throws Exception {
        String propertyName = "";
        String fieldName = "COLUMN";
        Assert.assertEquals("Not the same", fieldName.toUpperCase(), AnnotationUtils.preparePropertyName(propertyName, fieldName));
    }

    @Test
    public void hasColumnAnnotation() throws Exception {
        Field field = Person.class.getDeclaredField("firstname");
        Assert.assertTrue("Problem with annotation",AnnotationUtils.hasColumnAnnotation(field));
    }

    @Test
    public  void hasAnnotation() throws Exception{
        Field field = Person.class.getDeclaredField("firstname");
        Boolean result = AnnotationUtils.hasAnnotation(field, DBColumn.class);
        Assert.assertEquals(true,result);

    }



}