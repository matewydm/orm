package pl.edu.agh.wp.orm.mapper.annotation;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.mapper.ManyToOneMapper;

import java.util.List;

import static org.junit.Assert.*;

public class AnnotationManyToOneMapperTest {
    @Test
    public void getManyToOneReferences() throws Exception {
        ManyToOneMapper mapper = new AnnotationManyToOneMapper();

        List<DBManyToOneReference> list = mapper.getManyToOneReferences(Address.class);

        Assert.assertEquals(1,list.size());
    }

}