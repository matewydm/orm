package pl.edu.agh.wp.orm.mapper;

import pl.edu.agh.wp.orm.dto.DBColumnObject;

import java.lang.reflect.Field;
import java.util.List;

public interface ColumnMapper {
     List<DBColumnObject> getColumns(List<Field> fields);
}
