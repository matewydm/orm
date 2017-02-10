package pl.edu.agh.wp.orm.creator;

import pl.edu.agh.wp.orm.criterion.queries.AbstractCriterion;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.queries.DeleteQuery;
import pl.edu.agh.wp.orm.dto.queries.SelectQuery;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

import java.util.List;

public class DeleteQueryCreator implements QueryCreator {

    private DBTableObject tableObject;

    public DeleteQueryCreator(DBTableObject tableObject){
        this.tableObject = tableObject;
    }

    @Override
    public DBQuery createQuery(Object object) {
        DeleteQuery query = new DeleteQuery(tableObject.getFullName());
        query.doStartQuery();
        query.addId(tableObject,object);
        query.doEndQuery();
        return query;
    }
}
