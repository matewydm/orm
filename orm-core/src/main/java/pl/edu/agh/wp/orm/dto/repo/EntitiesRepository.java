package pl.edu.agh.wp.orm.dto.repo;

import pl.edu.agh.wp.orm.dto.DBTableObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntitiesRepository {

    private Map<Class,DBTableObject> repositories;

    public EntitiesRepository(){repositories = new ConcurrentHashMap<>();};


    public void addEntity(DBTableObject table){
        repositories.put(table.getEntity(),table);
    }

    public DBTableObject getTable(Class clazz){
        return repositories.get(clazz);
    }


}
