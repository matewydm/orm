package pl.edu.agh.wp.orm.criterion.queries.logical;

import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.criterion.queries.LogicalCriterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class ConjunctionCriterion extends LogicalCriterion {

    public ConjunctionCriterion(Criterion left, Criterion right) {
        super(left, right, DatabaseStatement.AND);
    }
}
