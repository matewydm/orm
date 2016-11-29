package pl.edu.agh.wp.orm.mapper.annotation;

import pl.edu.agh.wp.orm.mapper.ColumnMapper;
import pl.edu.agh.wp.orm.dto.DBColumnObject;

import java.util.Arrays;
import java.util.List;

public class AnnotationColumnMapper implements ColumnMapper {

    @Override
    public List<DBColumnObject> getColumns(Class clazz) {
       return null;
       // Arrays.stream(clazz.getFields()).filter()
    }
}
