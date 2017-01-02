package pl.edu.agh.wp.orm.creator;

import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.queries.SelectQuery;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

import java.util.List;

public class SelectQueryCreator implements QueryCreator{

    private DBTableObject tableObject;

    public SelectQueryCreator(DBTableObject tableObject){
        this.tableObject = tableObject;
    }

    @Override
    public DBQuery createQuery(Object object) {
        List<Criterion> criterionList = (List<Criterion>) object;
        SelectQuery query = new SelectQuery(tableObject.getFullName());
        query.doStartQuery();
        query.addCriterions(criterionList);
        query.doEndQuery();
        return query;
    }

}
