package pl.edu.agh.wp.orm.collections.factory.util;

import pl.edu.agh.wp.orm.collections.factory.CollectionFactory;
import pl.edu.agh.wp.orm.collections.factory.impl.ArrayListFactory;
import pl.edu.agh.wp.orm.collections.factory.impl.HashSetFactory;
import pl.edu.agh.wp.orm.collections.factory.impl.LinkedListFactory;
import pl.edu.agh.wp.orm.collections.factory.impl.TreeSetFactory;
import pl.edu.agh.wp.orm.collections.factory.impl.VectorFactory;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.lang.reflect.Field;
import java.util.*;

public class CollectionFactoryUtils {

    private static Map<Class,CollectionFactory> defaultCollections;

    static {
        defaultCollections = new HashMap<>();
        defaultCollections.put(List.class, new ArrayListFactory());
        defaultCollections.put(ArrayList.class, new ArrayListFactory());
        defaultCollections.put(LinkedList.class, new LinkedListFactory());
        defaultCollections.put(Vector.class, new VectorFactory());
        defaultCollections.put(Set.class, new HashSetFactory());
        defaultCollections.put(HashSet.class, new HashSetFactory());
        defaultCollections.put(TreeSet.class, new TreeSetFactory());
    }

    public static CollectionFactory getFactory(Field field) {
        Class collectionClass = field.getType();
        CollectionFactory collectionFactory = defaultCollections.get(collectionClass);
        if (collectionFactory == null) {
            throw new ORMException("Unexpected collection exception");
        }
        return collectionFactory;
    }

}
