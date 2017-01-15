package pl.edu.agh.wp.orm.session;

import org.apache.log4j.Logger;
import pl.edu.agh.wp.orm.criterion.Criteria;
import pl.edu.agh.wp.orm.criterion.CriteriaImpl;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSession implements Session {

    private Connection connection;
    Logger logger = Logger.getLogger(DefaultSession.class);

    public DefaultSession(EntitiesRepository entitiesInformation, Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Object object) {
        EntitiesRepository repository = EntitiesRepository.getInstance();
        repository.getTable(object.getClass());
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
            logger.error(e);
        }
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public Criteria createCriteria(Class clazz) {
        try {
            return new CriteriaImpl(connection.createStatement(),clazz);
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

}
