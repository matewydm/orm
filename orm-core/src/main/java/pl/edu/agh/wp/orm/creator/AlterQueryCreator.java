package pl.edu.agh.wp.orm.creator;

import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBIdObject;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.AlterQuery;
import pl.edu.agh.wp.orm.dto.queries.CreateQuery;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;

import java.util.List;

public class AlterQueryCreator extends QueryCreator{
    private DBTableObject tableObject;

    public AlterQueryCreator(DBTableObject tableObject){
        this.tableObject = tableObject;
    }

    @Override
    public DBQuery createQuery(Object object) {
        DBQuery query = new AlterQuery(tableObject.getFullName());
        query.doStartQuery();
        List<DBManyToOneReference> references = tableObject.getManyToOneReferences();
        DBIdObject id = tableObject.getIdObject();
        handleId(query,id);

        handleColumns(query, columns);
        handleReference(query, references);
        query.doEndQuery();
        return query;
    }
    }


}
