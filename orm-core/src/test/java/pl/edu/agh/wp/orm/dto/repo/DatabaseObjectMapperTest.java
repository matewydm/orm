package pl.edu.agh.wp.orm.dto.repo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

public class DatabaseObjectMapperTest {

    private static final String EXAMPLE_PACKAGE = "pl.edu.agh.wp.orm";
    private TableMapper tableMapper;
    private ORMFactory factory = new AnnotationORMFactory();
    @Before
    public void setUp() throws Exception {
        tableMapper = factory.getMapper();


    }

    @Test
    public void getEntities() throws Exception {
        AnnotationDatabaseObjectMapper mapper = new AnnotationDatabaseObjectMapper(EXAMPLE_PACKAGE,tableMapper);
        mapper.initializEentitiesRepository();
        Assert.assertNotNull(EntitiesRepository.getInstance() );
    }


}