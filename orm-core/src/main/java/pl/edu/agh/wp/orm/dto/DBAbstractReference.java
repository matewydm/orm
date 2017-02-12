package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

import java.lang.reflect.Field;

public abstract class DBAbstractReference extends DBColumnObject{

    protected String joinTable;
    protected Class joinedClass;
    protected DBFetchType fetch;

    public DBAbstractReference(Field field) {
        super(field);
    }

    public String getJoinTable() {
        return joinTable;
    }

    public void setJoinTable(String joinTable) {
        this.joinTable = joinTable;
    }

    public Class getJoinedClass() {
        return joinedClass;
    }

    public void setJoinedClass(Class joinedClass) {
        this.joinedClass = joinedClass;
    }

    public DBFetchType getFetch() {
        return fetch;
    }

    public void setFetch(DBFetchType fetch) {
        this.fetch = fetch;
    }

    public boolean isEager() {
        return this.fetch.equals(DBFetchType.EAGER);
    }

}
