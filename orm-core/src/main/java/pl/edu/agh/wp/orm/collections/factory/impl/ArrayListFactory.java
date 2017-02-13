package pl.edu.agh.wp.orm.collections.factory.impl;

import pl.edu.agh.wp.orm.collections.factory.CollectionFactory;

import java.util.ArrayList;
import java.util.Collection;

public class ArrayListFactory implements CollectionFactory {

    @Override
    public Collection getCollectionInstance() {
        return new ArrayList();
    }

}
