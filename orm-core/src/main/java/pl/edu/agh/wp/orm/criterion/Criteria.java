package pl.edu.agh.wp.orm.criterion;

import pl.edu.agh.wp.orm.criterion.queries.Criterion;

import java.util.List;

public interface Criteria {

    Criteria add(Criterion criterion);

    Criteria setMaxResult(Integer maxResult);

    List list();
}
