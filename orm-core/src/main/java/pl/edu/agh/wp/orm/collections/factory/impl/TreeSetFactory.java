package pl.edu.agh.wp.orm.collections.factory.impl;

import pl.edu.agh.wp.orm.collections.factory.CollectionFactory;

import java.util.Collection;
import java.util.TreeSet;

public class TreeSetFactory implements CollectionFactory {

    @Override
    public Collection getCollectionInstance() {
        return new TreeSet();
    }
}
