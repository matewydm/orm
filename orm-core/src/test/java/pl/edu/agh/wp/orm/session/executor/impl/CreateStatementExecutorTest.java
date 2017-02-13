package pl.edu.agh.wp.orm.session.executor.impl;


import org.junit.*;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.session.SessionFactory;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.Connection;
import java.sql.Statement;

public class CreateStatementExecutorTest {
    static SessionFactory factory;

    @BeforeClass
    public static void setUp() throws Exception {
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        factory = configuration.buildSessionFactory();

    }

    @AfterClass
    public static void tearDown() throws Exception {
        Connection connection = factory.openSession().getConnection();
        connection.createStatement().executeUpdate("DROP TABLE Pirson ");
        connection.createStatement().executeUpdate("DROP TABLE Personn ");
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

    @Test
    public void execute1() throws  Exception{
        String sqlString = "CREATE TABLE Personn (per_id Integer NOT NULL, name VARCHAR(20), lastname VARCHAR(20), age INTEGER, birth_date DATE);";
        Connection connection = factory.openSession().getConnection();
        Statement st = connection.createStatement();
        CreateStatementExecutor executor = new CreateStatementExecutor(st);
        Object o = executor.execute(sqlString);
        Assert.assertNotNull(o);
    }

}
