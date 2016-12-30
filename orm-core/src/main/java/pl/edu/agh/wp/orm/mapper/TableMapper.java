package pl.edu.agh.wp.orm.mapper;

import pl.edu.agh.wp.orm.dto.DBTableObject;

public interface TableMapper {

    DBTableObject getTable(Class clazz );

    void setColumnMapper(ColumnMapper columnMapper);

}
