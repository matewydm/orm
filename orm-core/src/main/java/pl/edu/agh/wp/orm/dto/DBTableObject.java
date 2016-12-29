package pl.edu.agh.wp.orm.dto;

import pl.ed.agh.wp.orm.annotations.DBColumn;

import java.util.List;


public class DBTableObject {
    private Class entity;

    private String schemaName;
    private String tableName;
    private String fullName;
    private List<DBColumnObject> columns;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public Class getEntity() {
        return entity;
    }

    public void setEntity(Class entity) {
        this.entity = entity;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<DBColumnObject> getColumns() {
        return columns;
    }

    public void setColumns(List<DBColumnObject> columns) {
        this.columns = columns;
    }
}
