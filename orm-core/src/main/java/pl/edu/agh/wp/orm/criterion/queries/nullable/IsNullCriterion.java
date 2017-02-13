package pl.edu.agh.wp.orm.criterion.queries.nullable;

import pl.edu.agh.wp.orm.criterion.queries.NullCriterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class IsNullCriterion extends NullCriterion {

    public IsNullCriterion(String column) {
        super(column, DatabaseStatement.NULL);
    }
}
