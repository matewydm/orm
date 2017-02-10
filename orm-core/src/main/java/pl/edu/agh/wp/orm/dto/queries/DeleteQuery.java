package pl.edu.agh.wp.orm.dto.queries;

import pl.edu.agh.wp.orm.criterion.queries.AbstractCriterion;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

import java.util.List;

public class DeleteQuery extends DBQuery {

    private String tableName;

    public DeleteQuery(String tableName) {
        super();
        this.tableName = tableName;
    }

    @Override
    public void doStartQuery() {
        super.doStartQuery();
        appendWithSpace(DatabaseStatement.DELETE);
        appendWithSpace(DatabaseStatement.FROM);
        appendWithSpace(tableName);
        appendWithSpace(tableName.toLowerCase());
    }

    public void addId(DBTableObject dbTableObject, Object object) {
        appendWithSpace(DatabaseStatement.WHERE);
        append(tableName.toLowerCase());
        append(".");
        appendEquation(dbTableObject.getIdObject().getColumnName(),dbTableObject.getIdObject().getSQLStringValue(object));

    }

    @Override
    public void doEndQuery() {
        super.doEndQuery();
        append(";");
    }
}
