package pl.edu.agh.wp.orm.dto.repo;

import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.exception.ORMNoSuchTableException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntitiesRepository {

    private static class LazyLoader {
        private static EntitiesRepository repository = new EntitiesRepository();
    }

    public static EntitiesRepository getInstance(){
        return LazyLoader.repository;
    }
    private Map<Class,DBTableObject> repositories;

    private EntitiesRepository(){repositories = new ConcurrentHashMap<>();};


    public void addEntity(DBTableObject table){
        repositories.put(table.getEntity(),table);
    }

    public DBTableObject getTable(Class clazz){
        DBTableObject dbTableObject = repositories.get(clazz);
        if(dbTableObject != null)
            return dbTableObject;
        throw  new ORMNoSuchTableException("Cannot find class "+clazz.getName());
    }


}
