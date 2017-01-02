package pl.edu.agh.wp.orm.dto.queries;

import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

import java.util.List;

public class SelectQuery extends DBQuery {

    private String tableName;

    public SelectQuery(String table){
        super();
        tableName = table;
    }

    @Override
    public void doStartQuery() {
        super.doStartQuery();
        appendWithSpace(DatabaseStatement.SELECT);
        appendWithSpace("*");
        appendWithSpace(DatabaseStatement.FROM);
        appendWithSpace(tableName);
        appendWithSpace(tableName.toLowerCase());
    }

    public void addCriterions(List<Criterion> criterionList){
        if (criterionList != null && criterionList.size() > 0)
            appendWithSpace(DatabaseStatement.WHERE);
        for (int i = 0; i < criterionList.size();) {
            Criterion criterion = criterionList.get(i);
            append(tableName.toLowerCase());
            append(".");
            appendWithSpace(criterion.toSqlQuery());
            if (++i < criterionList.size())
                appendWithSpace(DatabaseStatement.AND);
        }
    }

    @Override
    public void doEndQuery() {
        super.doEndQuery();
    }
}
