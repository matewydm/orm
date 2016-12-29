package pl.edu.agh.wp.orm.creator;

import pl.edu.agh.wp.orm.dto.queries.DBQuery;

public interface QueryCreator {
    DBQuery createQuery(Object object);
}
