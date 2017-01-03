package pl.edu.agh.wp.orm.session;

import pl.edu.agh.wp.orm.criterion.CriteriaImpl;

import java.util.List;

public interface Session {

    void save(Object object);

    void update(Object object);

    void delete (Object object);

    boolean isOpened();

    void close();

    CriteriaImpl createCriteria(Class clazz);

}
