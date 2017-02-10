package pl.edu.agh.wp.orm.mapper.annotation;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.mapper.ManyToOneMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnnotationManyToOneMapperTest {
    @Test
    public void getManyToOneReferences() throws Exception {
        ManyToOneMapper mapper = new AnnotationManyToOneMapper();
        List<Field> l = new ArrayList<>(1);
        l.add(Address.class.getDeclaredField("person"));
        List<DBManyToOneReference> list = mapper.getManyToOneReferences(l);

        Assert.assertEquals(1,list.size());
    }

}