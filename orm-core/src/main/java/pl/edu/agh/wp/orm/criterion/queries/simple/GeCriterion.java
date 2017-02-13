package pl.edu.agh.wp.orm.criterion.queries.simple;

import pl.edu.agh.wp.orm.criterion.queries.SimpleCriterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class GeCriterion extends SimpleCriterion {

    public GeCriterion(String column, Object value) {
        super(column, value, DatabaseStatement.GREATER_EQUAL);
    }
}
