package pl.edu.agh.wp.orm.creator;

import pl.ed.agh.wp.orm.annotations.enums.GenerationType;
import pl.edu.agh.wp.orm.dto.*;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.queries.InsertQuery;
import pl.edu.agh.wp.orm.exception.ORMException;

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
        handleIdColumn(query,object,tableObject.getIdObject());
        List<DBColumnObject> columns = tableObject.getColumns();
        DBColumnObject firstColumn = columns.get(0);
        query.appendColumnWithArgument(firstColumn.getColumnName(),firstColumn.getSQLStringValue(object));
        for(DBColumnObject column : columns.subList(1,columns.size())){
            query.append(",");
            query.appendColumnWithArgument(column.getColumnName(),column.getSQLStringValue(object));

        }
        for (DBManyToOneReference ref : tableObject.getManyToOneReferences()){
            query.append(",");
            query.appendColumnWithArgument(ref.getColumnName(),ref.getIdSQLString(object));
        }
        query.doEndQuery();
        return query;
    }

    private void handleIdColumn(DBQuery query, Object object, DBIdObject idObject) {
        if (!idObject.isAutoGenereted() || (idObject.getGenerationType().equals(GenerationType.SEQUENCE))){
            Object idValue = idObject.getValue(object);
            if(idValue == null)
                throw new ORMException("Cannot insert object with null id");
            String idColumnName = idObject.getColumnName();
            query.appendColumnWithArgument(idColumnName,idObject.getSQLStringValue(object));
            query.append(",");
        }

    }


}
