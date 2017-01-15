package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

public class DBOneToManyReference {
    private String column;
    private String joinTable;
    private Class jointedClass;
    private DBFetchType fetch;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
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
