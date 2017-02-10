package pl.edu.agh.wp.orm.creator;


import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;
import pl.ed.agh.wp.orm.annotations.enums.GenerationType;
import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBIdObject;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.CreateQuery;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

import java.util.List;


public class CreateQueryCreator implements QueryCreator{
    private DBTableObject tableObject;

    public CreateQueryCreator(DBTableObject tableObject){
        this.tableObject = tableObject;
    }

    @Override
    public DBQuery createQuery(Object object) {
        DBQuery query = new CreateQuery(tableObject.getFullName());
        query.doStartQuery();
        List<DBColumnObject> columns = tableObject.getColumns();
        List<DBManyToOneReference> references = tableObject.getManyToOneReferences();
        DBIdObject id = tableObject.getIdObject();
        handleId(query,id);

        handleColumns(query, columns);
       // handleReference(query, references);
        query.doEndQuery();
        return query;
    }

    private void handleId(DBQuery query, DBIdObject id){
        query.appendWithSpace(id.getColumnName());
        query.append(id.getConverter().getType().toString());
    }

    private void handleColumns(DBQuery query, List<DBColumnObject> columns) {
        for(DBColumnObject column : columns) {
            handleSimpleColumn(query, column);
        }
    }

    private void handleReference(DBQuery query, List<DBManyToOneReference> references){
        for(DBManyToOneReference ref : references){
            ref.getType().getType();
        }

    }

    private void handleSimpleColumn(DBQuery query, DBColumnObject column) {
        query.appendWithSpace(",");
        query.appendWithSpace(column.getColumnName());
        handleColumnType(query, column);
        if(!column.isNullable())
            query.appendWithSpace(DatabaseStatement.NOT_NULL);
        if(column.isUnique())
            query.appendWithSpace(DatabaseStatement.UNIQUE);
    }

    private void handleColumnType(DBQuery query, DBColumnObject column) {
        DatabaseTypes type = column.getConverter().getType();
        query.append(type.toString());
        if(type == DatabaseTypes.VARCHAR || type == DatabaseTypes.CHAR)
            query.append("(" + column.getMaxLength() + ")");
        else if (type == DatabaseTypes.NUMERIC)
            query.append("(" + column.getPrecision() + "," + column.getScale() + ")");
        //TODO
    }


}



