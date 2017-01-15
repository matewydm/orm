package pl.edu.agh.wp.orm.dto.repo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationColumnMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationIdMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationManyToOneMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationTableMapper;

public class DatabaseObjectMapperTest {

    private static final String EXAMPLE_PACKAGE = "pl.edu.agh.wp.orm";
    private TableMapper tableMapper;
    @Before
    public void setUp() throws Exception {
        tableMapper = new AnnotationTableMapper(new AnnotationColumnMapper(),new AnnotationIdMapper(),new AnnotationManyToOneMapper());


    }

    @Test
    public void getEntities() throws Exception {
        AnnotationDatabaseObjectMapper mapper = new AnnotationDatabaseObjectMapper(EXAMPLE_PACKAGE,tableMapper);

        Assert.assertNotNull( mapper.getEntities());
    }


}