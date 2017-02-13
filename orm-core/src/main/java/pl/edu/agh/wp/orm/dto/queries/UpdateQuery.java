package pl.edu.agh.wp.orm.dto.queries;


import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

import java.util.List;

public class UpdateQuery extends DBQuery{
    private String tableName;

    public UpdateQuery(String tableName) {
        super();
        this.tableName = tableName;
    }

    @Override
    public void doStartQuery() {
        super.doStartQuery();
        appendWithSpace(DatabaseStatement.UPDATE);
        appendWithSpace(tableName);
    }

    public void addId(DBTableObject dbTableObject, Object object) {
        appendWithSpace(DatabaseStatement.WHERE);
        appendEquation(dbTableObject.getIdObject().getColumnName(),dbTableObject.getIdObject().getSQLStringValue(object));
    }

    public void handleSetQuery(DBTableObject dbTableObject, Object object){
        appendWithSpace(DatabaseStatement.SET);
        List<DBColumnObject> columns = dbTableObject.getColumns();
        for(int i = 0 ; i < columns.size() - 1; i++){
            appendEquation(columns.get(i).getColumnName(),columns.get(i).getSQLStringValue(object));
            appendWithSpace(",");
        }
        appendEquation(columns.get(columns.size()-1).getColumnName(),columns.get(columns.size() - 1).getSQLStringValue(object));
        appendWithSpace("");
    }

    @Override
    public void doEndQuery() {
        super.doEndQuery();
        append(";");
    }
}
