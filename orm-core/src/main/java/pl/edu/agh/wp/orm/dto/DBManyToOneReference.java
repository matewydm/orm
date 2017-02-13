package pl.edu.agh.wp.orm.dto;

import org.reflections.ReflectionUtils;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.exception.ORMReflectionException;
import pl.edu.agh.wp.orm.postres.CommonKey;

import java.lang.reflect.Field;

public class DBManyToOneReference extends DBAbstractReference{

    public DBManyToOneReference(Field field) {
        super(field);
    }

    public String  getIdSQLString(Object o){
        Object idObject = getIdValue(o);
        if (idObject != null)
            return getConverter().getAsString(idObject);
        return CommonKey.NULL;
    }
    public Object getIdValue(Object o){
        Field id =  ReflectionUtils.getAllFields(field.getType()).stream().filter(AnnotationUtils::hasIdAnnotation).findAny().get();
        id.setAccessible(true);
        Object idObject = getValue(o);
        try {
            return id.get(idObject);
        } catch (IllegalAccessException e) {
            throw new ORMReflectionException(e);
        }

    }
}
