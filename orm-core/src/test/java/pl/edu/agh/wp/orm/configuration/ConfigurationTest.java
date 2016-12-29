package pl.edu.agh.wp.orm.configuration;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void addPropertiesTest() throws Exception {
        Configuration configuration = new Configuration()
                .addProperties("driver_class","org.postgresql.Driver")
                .addProperties("db_url","jdbc:postgresql://localhost:5432/postgres")
                .addProperties("user","postgres")
                .addProperties("password","dare");

        Assert.assertTrue(configuration.getProperty("driver_class").equals("org.postgresql.Driver"));
    }
}
