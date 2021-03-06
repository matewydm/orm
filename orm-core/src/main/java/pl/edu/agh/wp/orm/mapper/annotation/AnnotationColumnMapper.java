package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.Temporal;
import pl.ed.agh.wp.orm.annotations.Type;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.converter.DefaultTypeRegister;
import pl.edu.agh.wp.orm.converter.TemporalRegister;
import pl.edu.agh.wp.orm.converter.TypeRegister;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.ColumnMapper;
import pl.edu.agh.wp.orm.dto.DBColumnObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationColumnMapper extends TypeMatcher implements ColumnMapper{

    @Override
    public List<DBColumnObject> getColumns(List<Field> fields) {
        List<DBColumnObject> objects =
                fields.stream().map(this::prepareColumn).
                collect(Collectors.toList());

        return objects;

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
        dbColumn.setConverter(getType(field));
        return dbColumn;
    }

}
