package pl.edu.agh.wp.orm.session;

import java.util.List;

public interface Session {

    void save(Object object);

    void update(Object object);

    List<Object> find(String query);

    void delete (Object object);

}
