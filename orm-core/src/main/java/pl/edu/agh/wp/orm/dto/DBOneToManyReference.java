package pl.edu.agh.wp.orm.dto;

import pl.edu.agh.wp.orm.exception.ORMException;

import java.lang.reflect.Field;

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

    @Override
    public boolean isUnique() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNullable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxLength() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getScale() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPrecision() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUnique(boolean unique) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNullable(boolean nullable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaxLength(int maxLength) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setScale(int scale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPrecision(int precision) {
        throw new UnsupportedOperationException();
    }
}
