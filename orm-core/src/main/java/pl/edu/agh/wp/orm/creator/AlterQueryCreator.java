package pl.edu.agh.wp.orm.creator;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBIdObject;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.AlterQuery;
import pl.edu.agh.wp.orm.dto.queries.CreateQuery;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;

import java.util.List;

public class AlterQueryCreator implements QueryCreator {
    private DBTableObject tableObject;
    private DBManyToOneReference reference;
    public AlterQueryCreator(DBTableObject tableObject,DBManyToOneReference ref){
        this.tableObject = tableObject;
        this.reference = ref;
    }

    @Override
    public DBQuery createQuery(Object object) {
        DBQuery query = new AlterQuery(tableObject.getFullName(),reference.getColumnName(),reference.getJoinTable());
        query.doStartQuery();
        query.doEndQuery();
        return query;
    }



}
