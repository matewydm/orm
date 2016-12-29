package pl.edu.agh.wp.orm.session;

import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.session.DefaultSession;
import pl.edu.agh.wp.orm.session.Session;
import pl.edu.agh.wp.orm.session.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DefaultSessionFactory implements SessionFactory {

    Properties properties;

    public DefaultSessionFactory(Properties propertiesList) {
        this.properties = propertiesList;
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
                System.out.println("Connected to the database");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        DefaultSession defaultSession = new DefaultSession(new EntitiesRepository(),connection);
        return defaultSession;
    }
}
