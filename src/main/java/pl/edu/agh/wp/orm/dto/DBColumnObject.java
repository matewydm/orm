package pl.edu.agh.wp.orm.dto;

import pl.edu.agh.wp.orm.postres.DatabaseTypes;

import java.lang.reflect.Field;

public class DBColumnObject {

    private Field field;
    private String columnName;
    private boolean unique;
    private boolean nullable;
    private int maxLength;
    private int scale;
    private int precision;
    private DatabaseTypes databaseType;

    public DBColumnObject(Field field){
        this.field = field;
        //To musi byc na zewnatrz zeby mozna bylo xml tworzc obiekt DBVolumnObject
//        DBColumn annotation = field.getAnnotation(DBColumn.class);
//        setColumnName(AnnotationUtils.preparePropertyName(annotation.name(),field.getName()));
//        setUnique(annotation.unique());
//        setMaxLength(annotation.maxLength());
//        setNullable(annotation.nullable());
//        setScale(annotation.scale());
//        setPrecision(annotation.precision());


    }

    public String getColumnName() {
        return columnName;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isNullable() {
        return nullable;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getScale() {
        return scale;
    }

    public int getPrecision() {
        return precision;
    }

    public DatabaseTypes getDatabaseType() {
        return databaseType;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }


    /**
     * To trzbeba gdzie indziej inicjowac zeby mozna swoje typy nadpisywac @see pl.edu.agh.wp.orm.annotations.Type
     * @param databaseType
     */
    public void setDatabaseType(DatabaseTypes databaseType) {
        this.databaseType = databaseType;
    }

    public Object getValue(Object o){
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}


