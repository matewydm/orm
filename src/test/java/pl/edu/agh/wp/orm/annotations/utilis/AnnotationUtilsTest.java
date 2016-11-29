package pl.edu.agh.wp.orm.annotations.utilis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.example.Person;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class AnnotationUtilsTest {

    Person person;
    @Before
    public void prepareObject(){
        person = new Person();
    }

    @Test
    public void preparePropertyName() throws Exception {
        String propertyName = "test";
        String fieldName = "TTT";
        Assert.assertEquals("Not the same", propertyName, AnnotationUtils.preparePropertyName(propertyName, fieldName));
    }

    @Test
    public void hasColumnAnnotation() throws Exception {
        Field field = Person.class.getDeclaredField("firstname");
        Assert.assertTrue("Problem with annotation",AnnotationUtils.hasColumnAnnotation(field));
    }

}