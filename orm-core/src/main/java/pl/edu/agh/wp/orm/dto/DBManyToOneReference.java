package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

public class DBManyToOneReference {
    private String joinColumn;
    private String joinTable;
    private Class jointedClass;
    private DBFetchType fetch;

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
}
