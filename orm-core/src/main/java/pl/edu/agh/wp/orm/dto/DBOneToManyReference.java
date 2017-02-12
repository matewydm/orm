package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public class DBOneToManyReference extends DBAbstractReference{

    public DBOneToManyReference(Field field) {
        super(field);
    }

    public void setValue(Object entity,Object dataBaseField){
        try {
            field.set(entity,dataBaseField);
        } catch (IllegalAccessException   e) {
            throw new ORMException("Cannot set Object ",e);
        }
    }
}
