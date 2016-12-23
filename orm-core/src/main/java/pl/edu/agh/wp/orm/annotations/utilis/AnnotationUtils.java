package pl.edu.agh.wp.orm.annotations.utilis;

import pl.ed.agh.wp.orm.annotations.DBColumn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationUtils {
    public static String preparePropertyName(String annotationName, String fieldName) {
        if (!annotationName.isEmpty()) return annotationName;
        else return fieldName.toUpperCase();
    }

    public static Boolean hasColumnAnnotation(Field field) {
        return field.getAnnotation(DBColumn.class) != null ? Boolean.TRUE : Boolean.FALSE;
    }
    public static Boolean hasAnnotation(Field field, Class<? extends Annotation> annotation){
        Annotation a = field.getAnnotation(annotation);
        return a != null;
    }

}
