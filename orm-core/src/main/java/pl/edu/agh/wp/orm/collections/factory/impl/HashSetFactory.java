package pl.edu.agh.wp.orm.collections.factory.impl;


import pl.edu.agh.wp.orm.collections.factory.CollectionFactory;

import java.util.Collection;
import java.util.HashSet;

public class HashSetFactory implements CollectionFactory{

    @Override
    public Collection getCollectionInstance() {
        return new HashSet();
    }

}
