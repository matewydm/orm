package pl.edu.agh.wp.orm.collections.factory.impl;

import pl.edu.agh.wp.orm.collections.factory.CollectionFactory;

import java.util.Collection;
import java.util.LinkedList;

public class LinkedListFactory implements CollectionFactory {

    @Override
    public Collection getCollectionInstance() {
        return new LinkedList();
    }
}
