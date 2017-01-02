package pl.edu.agh.wp.orm.criterion.queries;

import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class ArrayCriterion extends Criterion {

    Object[] array;

    public ArrayCriterion(String column, Object[] array) {
        this.column = column;
        this.array = array;
        buildQuery();
    }

    @Override
    public void buildQuery() {
        appendWithSpace(column);
        appendWithSpace(DatabaseStatement.IN);
        append("(");
        for (int i = 0; i < array.length;){
            if (array[i] instanceof String)
                handleString(array[i]);
            else
                appendArgument(array[i]);
            if (++i < array.length)
                append(",");
        }
        append(")");
    }


}
