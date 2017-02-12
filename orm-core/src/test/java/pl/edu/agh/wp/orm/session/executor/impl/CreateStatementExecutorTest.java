package pl.edu.agh.wp.orm.session.executor.impl;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.session.SessionFactory;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.Connection;
import java.sql.Statement;

public class CreateStatementExecutorTest {
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
        String sqlString = "CREATE TABLE Pirson (PirsonID INTEGER, CUDO_AGE INTEGER, Name VARCHAR(100), firstname VARCHAR(100), date DATE);";
        Connection connection = factory.openSession().getConnection();
        Statement st = connection.createStatement();
        CreateStatementExecutor executor = new CreateStatementExecutor(st);
        Object o = executor.execute(sqlString);
        Assert.assertNotNull(o);
    }
}