package pl.edu.agh.wp.orm.criterion.queries;

public class LogicalCriterion extends AbstractCriterion {

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
