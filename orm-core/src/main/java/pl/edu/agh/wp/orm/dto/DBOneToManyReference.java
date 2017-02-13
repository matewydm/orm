package pl.edu.agh.wp.orm.dto;

import pl.edu.agh.wp.orm.collections.factory.util.CollectionFactoryUtils;
import pl.edu.agh.wp.orm.collections.factory.CollectionFactory;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.lang.reflect.Field;
import java.util.Collection;

public class DBOneToManyReference extends DBAbstractReference{

    public DBOneToManyReference(Field field) {
        super(field);
    }

    public void setValue(Object entity,Collection dataBaseField){
        CollectionFactory collectionFactory = CollectionFactoryUtils.getFactory(field);
        Collection collection = collectionFactory.getCollectionInstance();
        collection.addAll(dataBaseField);
        try {
            field.set(entity,collection);
        } catch (IllegalAccessException e) {
            throw new ORMException("Cannot set collection ", e);
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
