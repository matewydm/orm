package pl.edu.agh.wp.orm.criterion.queries.simple;

import pl.edu.agh.wp.orm.criterion.queries.SimpleCriterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class LikeCriterion extends SimpleCriterion {

    public LikeCriterion(String column, Object value) {
        super(column, value, DatabaseStatement.LIKE);
    }
}
