package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.Temporal;
import pl.ed.agh.wp.orm.annotations.Type;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.ColumnMapper;
import pl.edu.agh.wp.orm.dto.DBColumnObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
        getType(field,dbColumn);
        //TODO jak ma adnotacje @TypeConverter to ustaiwc patrac na nias

       // dbColumn.setDatabaseType();

        return dbColumn;
    }

    private TypeConverter getType(Field field, DBColumnObject dbColumn) {
        if(AnnotationUtils.hasAnnotation(field, Type.class))
            return handleTypeAnnotation(field);
        if(AnnotationUtils.hasAnnotation(field, Temporal.class))
            return handleTemporalAnnotation(field);
        return null;
    }

    private TypeConverter handleTemporalAnnotation(Field field) {
        Temporal annotation = field.getAnnotation(Temporal.class);
        TemporalType type = annotation.type();
        return null;
    }

    private TypeConverter handleTypeAnnotation(Field field) {
        Type type = field.getAnnotation(Type.class);

        Class<? extends TypeConverter> converter = type.converter();
        try {
           return converter.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new ORMException("No access to default constructor",e) ;
        } catch (IllegalAccessException e) {
            throw new ORMException("No access to default constructor",e) ;
        } catch (InstantiationException e) {
            throw new ORMException("No access to default constructor",e) ;
        } catch (InvocationTargetException e) {
            throw new ORMException("No access to default constructor",e) ;

        }

    }

}
