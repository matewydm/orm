package pl.edu.agh.wp.orm.configuration;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void addPropertiesTest() throws Exception {
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        Assert.assertTrue(configuration.getProperty("driver_class").equals("org.postgresql.Driver"));
    }
}
