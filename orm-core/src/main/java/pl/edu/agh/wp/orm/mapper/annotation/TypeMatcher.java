package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.Temporal;
import pl.ed.agh.wp.orm.annotations.Type;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.converter.DefaultTypeRegister;
import pl.edu.agh.wp.orm.converter.TemporalRegister;
import pl.edu.agh.wp.orm.converter.TypeRegister;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class TypeMatcher {

    private TypeRegister register = new DefaultTypeRegister();
    private TemporalRegister temporalRegister = new TemporalRegister();

    protected TypeConverter getType(Field field) {
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
