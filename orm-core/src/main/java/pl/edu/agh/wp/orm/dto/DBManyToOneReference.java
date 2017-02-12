package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

import java.lang.reflect.Field;

public class DBManyToOneReference extends DBAbstractReference{

    public DBManyToOneReference(Field field) {
        super(field);
    }


}
