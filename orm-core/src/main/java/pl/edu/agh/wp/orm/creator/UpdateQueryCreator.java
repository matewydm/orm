package pl.edu.agh.wp.orm.creator;


import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.queries.UpdateQuery;


public class UpdateQueryCreator implements QueryCreator{
    private DBTableObject tableObject;

    public UpdateQueryCreator(DBTableObject tableObject){
        this.tableObject = tableObject;
    }

    @Override
    public DBQuery createQuery(Object object) {
        UpdateQuery query = new UpdateQuery(tableObject.getFullName());
        query.doStartQuery();
        query.handleSetQuery(tableObject,object);
        query.addId(tableObject,object);
        query.doEndQuery();
        return query;
    }
}
