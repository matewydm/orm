package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;
import pl.edu.agh.wp.orm.exception.ReflectionORMException;

import java.lang.reflect.Field;

public class DBManyToOneReference extends DBColumnObject{

    private String joinTable;
    private Class jointedClass;
    private DBFetchType fetch;

    public DBManyToOneReference(Field field) {
        super(field);
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



}
