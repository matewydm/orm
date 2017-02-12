package pl.edu.agh.wp.orm.dto.repo;

import pl.edu.agh.wp.orm.dto.DBTableObject;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntitiesRepository implements Iterable<DBTableObject> {

    @Override
    public Iterator<DBTableObject> iterator() {
        return repositories.values().iterator();
    }

    private static class LazyLoader {
        private static EntitiesRepository repository = new EntitiesRepository();
    }

    public static EntitiesRepository getInstance(){
        return LazyLoader.repository;
    }
    private Map<Class,DBTableObject> repositories;

    private EntitiesRepository(){repositories = new ConcurrentHashMap<>();}


    public void addEntity(DBTableObject table){
        repositories.put(table.getEntity(),table);
    }

    public DBTableObject getTable(Class clazz){
        return repositories.get(clazz);
    }


}
