package pl.edu.agh.wp.orm.criterion.queries.nullable;

import pl.edu.agh.wp.orm.criterion.queries.NullCriterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class IsNotNullCriterion extends NullCriterion {

    public IsNotNullCriterion(String column) {
        super(column, DatabaseStatement.NOT_NULL);
    }
}
