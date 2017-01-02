package pl.edu.agh.wp.orm.criterion.queries;

import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class LogicalCriterion extends Criterion {

    private Criterion left;
    private Criterion right;

    public LogicalCriterion(Criterion left, Criterion right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        buildQuery();
    }

    @Override
    public void buildQuery() {
        appendWithSpace(left.toSqlQuery());
        appendWithSpace(operator);
        appendWithSpace(right.toSqlQuery());
    }
}
