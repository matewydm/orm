package pl.edu.agh.wp.orm.creator;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.queries.InsertQuery;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

import java.util.List;

public class InsertQueryCreator implements QueryCreator {
    private DBTableObject tableObject;

    public InsertQueryCreator(DBTableObject tableObject){
        this.tableObject = tableObject;
    }


    @Override
    public DBQuery createQuery(Object object) {
        DBQuery query = new InsertQuery(tableObject.getFullName());

        query.doStartQuery();
        List<DBColumnObject> columns = tableObject.getColumns();
        DBColumnObject firstColumn = columns.get(0);
        query.appendColumnWithArgument(firstColumn.getColumnName(),firstColumn.getSQLStringValue(object));
        for(DBColumnObject column : columns.subList(1,columns.size())){
            query.append(",");
            query.appendColumnWithArgument(column.getColumnName(),column.getSQLStringValue(object));

        }
        query.doEndQuery();
        return query;
    }
}
