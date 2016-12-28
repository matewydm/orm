package pl.edu.agh.wp.orm.mapper;

import pl.edu.agh.wp.orm.dto.DBColumnObject;

import java.util.List;

public interface ColumnMapper {
     List<DBColumnObject> getColumns(Class clazz );
}
