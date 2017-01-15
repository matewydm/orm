package pl.edu.agh.wp.orm.criterion.queries;

public class NullCriterion extends AbstractCriterion {

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
