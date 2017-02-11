package pl.edu.agh.wp.orm.criterion;

import pl.edu.agh.wp.orm.criterion.queries.AbstractCriterion;
import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;

import java.util.List;

public interface Criteria {

    Criteria add(Criterion criterion);

    Criteria setMaxResult(Integer maxResult);

    DBQuery build();

    List list();

    Object uniqueResult();
}
