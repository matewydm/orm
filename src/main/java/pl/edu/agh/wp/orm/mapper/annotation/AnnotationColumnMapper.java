package pl.edu.agh.wp.orm.mapper.annotation;

import pl.edu.agh.wp.orm.annotations.DBColumn;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.mapper.ColumnMapper;
import pl.edu.agh.wp.orm.dto.DBColumnObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationColumnMapper implements ColumnMapper {

    @Override
    public List<DBColumnObject> getColumns(Class clazz) {
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(AnnotationUtils::hasColumnAnnotation).collect(Collectors.toList());

        return null;
        // Arrays.stream(clazz.getFields()).filter()
    }

    private DBColumnObject prepareColumn(Field field) {
        DBColumnObject dbColumn = new DBColumnObject(field);
        DBColumn annotation = field.getAnnotation(DBColumn.class);
        dbColumn.setColumnName(AnnotationUtils.preparePropertyName(annotation.name(),field.getName()));
        dbColumn.setUnique(annotation.unique());
        dbColumn.setMaxLength(annotation.maxLength());
        dbColumn.setNullable(annotation.nullable());
        dbColumn.setScale(annotation.scale());
        dbColumn.setPrecision(annotation.precision());

        return dbColumn;
    }

}
