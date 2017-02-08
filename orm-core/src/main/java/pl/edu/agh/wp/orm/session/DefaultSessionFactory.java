package pl.edu.agh.wp.orm.session;

import pl.edu.agh.wp.orm.dto.repo.DatabaseObjectMapper;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DefaultSessionFactory implements SessionFactory {

    private EntitiesRepository repository;
    private Properties properties;

    private Logger logger = Logger.getLogger(DefaultSessionFactory.class);

    public DefaultSessionFactory(Properties propertiesList, DatabaseObjectMapper mapper) {
        this.properties = propertiesList;
        this.repository = mapper.getEntitiesRepository();
    }

    public Session openSession() {

        String dbUrl = properties.getProperty("db_url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Connection connection = null;

        try {
            Class.forName(properties.getProperty("driver_class"));
            connection = DriverManager.getConnection(dbUrl, user, password);
            if (connection != null) {
                logger.debug("Connected to database");
            }
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        } catch (SQLException ex) {
            logger.error(ex);
        }

        DefaultSession defaultSession = new DefaultSession(repository,connection);
        return defaultSession;
    }
}
