package pl.edu.agh.wp.orm.annotations.utilis;

import pl.edu.agh.wp.orm.annotations.DBColumn;
import pl.edu.agh.wp.orm.postres.CommonKey;

import java.lang.reflect.Field;

public class AnnotationUtils {
    public static String preparePropertyName(String annotationName, String fieldName) {
        if (!annotationName.equals(CommonKey.DEFAULT)) return annotationName;
        else return fieldName.toUpperCase();
    }

    public static Boolean hasColumnAnnotation(Field field) {
        return field.getAnnotation(DBColumn.class) != null ? Boolean.TRUE : Boolean.FALSE;
    }

}
