package pl.edu.agh.wp.orm.criterion.queries;

import pl.edu.agh.wp.orm.criterion.queries.Criterion;

public class NullCriterion extends Criterion {

    public NullCriterion(String column, String operator) {
        this.column = column;
        this.operator = operator;
        buildQuery();
    }

    @Override
    public void buildQuery() {
        appendWithSpace(column);
        appendWithSpace(operator);
    }
}
