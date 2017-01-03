package pl.edu.agh.wp.orm.creator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.criterion.Restrictions;
import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationColumnMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationTableMapper;
import pl.edu.agh.wp.orm.session.Session;

import java.util.ArrayList;
import java.util.List;

public class SelectQueryCreatorTest {

    TableMapper mapper ;

    @Before
    public void setUp() throws Exception {
        mapper = new AnnotationTableMapper();
        mapper.setColumnMapper(new AnnotationColumnMapper());

    }

    @Test
    public void toSQLStringTest() throws Exception {

        String expected = "SELECT * FROM PERSON person WHERE person.name LIKE '%'  AND person.age BETWEEN 4 AND 3 ";
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add( Restrictions.like("name", "%") );
        criterionList.add( Restrictions.between("age",4,3));

        QueryCreator queryCreator = new SelectQueryCreator(mapper.getTable(Person.class));

        DBQuery query = queryCreator.createQuery(criterionList);
        Assert.assertEquals(expected,query.getSQLQuery());
    }

}