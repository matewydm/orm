package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;
import pl.edu.agh.wp.orm.exception.ReflectionORMException;

import java.lang.reflect.Field;

public class DBManyToOneReference {
    private String joinColumn;
    private String joinTable;
    private Class jointedClass;
    private DBFetchType fetch;
    private Field field;
    private TypeConverter type;

    public String getJoinColumn() {
        return joinColumn;
    }

    public void setJoinColumn(String joinColumn) {
        this.joinColumn = joinColumn;
    }

    public String getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable;
    }

    public Class getJointedClass() {
        return jointedClass;
    }

    public void setJointedClass(Class jointedClass) {
        this.jointedClass = jointedClass;
    }

    public DBFetchType getFetch() {
        return fetch;
    }

    public void setFetch(DBFetchType fetch) {
        this.fetch = fetch;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public TypeConverter getType() {
        return type;
    }

    public void setType(TypeConverter type) {
        this.type = type;
    }

    public Object getValue(Object o){
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new ReflectionORMException("Cannot get value from "+o.getClass().getName(),e);
        }
    }

    public void setValue(Object object,Object value){
        try {
            field.set(object,value);
        } catch (IllegalAccessException e) {
            throw new ReflectionORMException("Cannot set vale from object",e);
        }
    }

}
