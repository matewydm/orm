package pl.edu.agh.wp.orm.session;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;

public class SessionFactoryTest {

    @Test
    public void openSessionFromConfigurationTest() throws Exception {

        Configuration configuration = new Configuration()
                .addProperties("driver_class","org.postgresql.Driver")
                .addProperties("db_url","jdbc:postgresql://localhost:5432/postgres")
                .addProperties("user","postgres")
                .addProperties("password","dare");

        Session session = configuration.buildSessionFactory().openSession();

        Assert.assertTrue(session.isOpened());

        session.close();
    }
}
