package pl.edu.agh.wp.orm.creator;

import pl.edu.agh.wp.orm.criterion.queries.AbstractCriterion;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.queries.SelectQuery;

import java.util.List;

public class SelectQueryCreator implements QueryCreator{

    private DBTableObject tableObject;

    public SelectQueryCreator(DBTableObject tableObject){
        this.tableObject = tableObject;
    }

    @Override
    public DBQuery createQuery(Object object) {
        List<AbstractCriterion> criterionList = (List<AbstractCriterion>) object;
        SelectQuery query = new SelectQuery(tableObject.getFullName());
        query.doStartQuery();
        query.addCriterions(criterionList);
        query.doEndQuery();
        return query;
    }

}
