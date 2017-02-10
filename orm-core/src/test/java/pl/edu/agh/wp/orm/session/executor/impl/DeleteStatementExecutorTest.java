package pl.edu.agh.wp.orm.session.executor.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.session.SessionFactory;

import java.sql.Connection;
import java.sql.Statement;

public class DeleteStatementExecutorTest {

    SessionFactory factory;
    @Before
    public void setUp() throws Exception {
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        factory = configuration.buildSessionFactory();

    }

    @Test
    public void execute() throws Exception {
        String sqlString = "DELETE FROM Person person WHERE person.per_id = -1;";
        Connection connection = factory.openSession().getConnection();
        Statement st = connection.createStatement();

        DeleteStatementExecutor executor = new DeleteStatementExecutor(st);
        Object o = executor.execute(sqlString);
        Assert.assertNull(o);
    }


}
