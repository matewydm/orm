package pl.edu.agh.wp.orm.criterion.queries.simple;

import pl.edu.agh.wp.orm.criterion.queries.SimpleCriterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class EqCriterion extends SimpleCriterion {

    public EqCriterion(String column, Object value) {
        super(column, value, DatabaseStatement.EQUAL);
    }

}
