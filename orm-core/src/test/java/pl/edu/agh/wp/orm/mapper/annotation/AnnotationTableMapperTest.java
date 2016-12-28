package pl.edu.agh.wp.orm.mapper.annotation;

import org.junit.Test;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.mapper.TableMapper;

import static org.junit.Assert.*;

public class AnnotationTableMapperTest {

    @Test
    public void getTable() throws Exception {
        TableMapper mapper = new AnnotationTableMapper();
        mapper.setColumnMapper(new AnnotationColumnMapper());
        DBTableObject table = mapper.getTable(Person.class);

    }

}