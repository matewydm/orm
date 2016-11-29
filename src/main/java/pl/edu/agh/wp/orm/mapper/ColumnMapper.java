package pl.edu.agh.wp.orm.mapper;

import pl.edu.agh.wp.orm.dto.DBColumnObject;

import java.util.List;

public interface ColumnMapper {
    public List<DBColumnObject> getColumns(Class clazz );
}
