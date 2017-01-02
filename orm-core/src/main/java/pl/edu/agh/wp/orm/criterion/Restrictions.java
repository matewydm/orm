package pl.edu.agh.wp.orm.criterion;

import pl.edu.agh.wp.orm.criterion.queries.BetweenCriterion;
import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.criterion.queries.SimpleCriterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class Restrictions {

    public static Criterion like(String column, Object value) {
        return new SimpleCriterion(column,value,DatabaseStatement.LIKE);
    }

    public static Criterion between(String column, Object minValue, Object maxValue) {
       return new BetweenCriterion(column,minValue,maxValue);
    }

}
