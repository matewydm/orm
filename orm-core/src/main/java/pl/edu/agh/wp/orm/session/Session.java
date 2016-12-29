package pl.edu.agh.wp.orm.session;

import java.util.List;

public interface Session {

    void save(Object object);

    void update(Object object);



    void delete (Object object);

}
