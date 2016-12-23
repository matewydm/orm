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

public class AnnotationColumnMapper implements ColumnMapper {

    TypeRegister register = new DefaultTypeRegister();
    TemporalRegister temporalRegister = new TemporalRegister();

    @Override
    public List<DBColumnObject> getColumns(Class clazz) {
        List<DBColumnObject> objects = Arrays.stream(clazz.getDeclaredFields())
                .filter(AnnotationUtils::hasColumnAnnotation)
                .map(this::prepareColumn).
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

    private TypeConverter getType(Field field) {
        if(AnnotationUtils.hasAnnotation(field, Type.class))
            return handleTypeAnnotation(field);
        else if(AnnotationUtils.hasAnnotation(field, Temporal.class))
            return handleTemporalAnnotation(field);
        else
            return handleDefaultType(field);

    }

    private TypeConverter handleDefaultType(Field field) {
        Class clazz = field.getType();
        return register.getConverter(clazz);
    }

    private TypeConverter handleTemporalAnnotation(Field field) {
        Temporal annotation = field.getAnnotation(Temporal.class);
        TemporalType type = annotation.type();
        return temporalRegister.getConverter(type);
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
