package pl.edu.agh.wp.orm.session;

import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSession implements Session {

    private Connection connection;

    public DefaultSession(EntitiesRepository entitiesInformation, Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public boolean isOpened() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
