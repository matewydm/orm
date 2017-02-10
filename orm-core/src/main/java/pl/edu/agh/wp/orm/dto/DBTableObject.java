package pl.edu.agh.wp.orm.dto;

import org.apache.log4j.Logger;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class DBTableObject {

    Logger logger = Logger.getLogger(DBTable.class);

    private Class entity;
    private String schemaName;
    private String tableName;
    private String fullName;
    private List<DBColumnObject> columns;
    private DBIdObject idObject;
    private List<DBManyToOneReference> manyToOneReferences;
    private List<DBOneToManyReference> dbOneToManyReferences;

    public List<DBOneToManyReference> getOneToManyReference() {
        return dbOneToManyReferences;
    }

    public void setDbOneToManyReferences(List<DBOneToManyReference> dbOneToManyReferences) {
        this.dbOneToManyReferences = dbOneToManyReferences;
    }

    public DBIdObject getIdObject() {
        return idObject;
    }

    public void setIdObject(DBIdObject idObject) {
        this.idObject = idObject;
    }

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

    public List<DBManyToOneReference> getManyToOneReferences() {
        return manyToOneReferences;
    }

    public void setManyToOneReferences(List<DBManyToOneReference> manyToOneReferences) {
        this.manyToOneReferences = manyToOneReferences;
    }

    public Object getPersistedInstance() {
        try {
            return entity.getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            logger.debug(e);
            throw new ORMException("Creating new persisted instance");
        }
    }
}
