package pl.edu.agh.wp.orm.mapper.annotation;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.example.pancakes.Zamowienia;
import pl.edu.agh.wp.orm.mapper.OneToManyMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationOneToManyMapperTest {
    @Test
    public void getManyToOneReferences() throws Exception {
        OneToManyMapper mapper = new AnnotationOneToManyMapper();
        List<Field> list = new ArrayList<>();
        list.add(Zamowienia.class.getDeclaredField("artykuly"));
       Assert.assertNotNull(mapper.getOneToManyReferences(list));
    }

}