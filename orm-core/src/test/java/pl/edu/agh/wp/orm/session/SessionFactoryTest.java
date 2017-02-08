package pl.edu.agh.wp.orm.session;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;

public class SessionFactoryTest {

    @Test
    public void openSessionFromConfigurationTest() throws Exception {

        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        Session session = configuration.buildSessionFactory().openSession();

        Assert.assertTrue(session.isOpened());

        session.close();
    }
}
