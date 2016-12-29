package pl.edu.agh.wp.orm.session;

public interface Session {

    void save(Object object);

    void update(Object object);

    void delete (Object object);

    boolean isOpened();

    void close();
}
