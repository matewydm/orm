package pl.edu.agh.wp.orm.annotations.utilis;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBId;
import pl.ed.agh.wp.orm.annotations.DBManyToOne;
import pl.ed.agh.wp.orm.annotations.DBOneToMany;
import pl.edu.agh.wp.orm.mapper.ManyToOneMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationUtils {
    public static String preparePropertyName(String annotationName, String fieldName) {
        if (!annotationName.isEmpty()) return annotationName;
        else return fieldName;
    }

    public static Boolean hasColumnAnnotation(Field field) {
        return field.getAnnotation(DBColumn.class) != null ? Boolean.TRUE : Boolean.FALSE;
    }
    public static Boolean hasAnnotation(Field field, Class<? extends Annotation> annotation){
        Annotation a = field.getAnnotation(annotation);
        return a != null;
    }

    public static Boolean hasManyToOneAnnotation(Field field){
        return hasAnnotation(field, DBManyToOne.class);
    }

    public static Boolean hasOneToManyAnnotation(Field field){
        return hasAnnotation(field, DBOneToMany.class);
    }

    public static Boolean hasIdAnnotation(Field field){
        return hasAnnotation(field, DBId.class);
    }

}
