package pl.edu.agh.wp.orm.session.executor.impl;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.session.SessionFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

public class UpdateStatementExecutorTest {
    SessionFactory factory;
    String date;

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
        date = new java.sql.Date((new Date()).getTime()).toString();

    }
//
//    @Test
//    public void execute() throws Exception {
//        String sqlString = "UPDATE czekoladki SET name = 'Dare' WHERE idczekoladki = 'b01';";
//        Connection connection = factory.openSession().getConnection();
//        Statement st = connection.createStatement();
//        UpdateStatementExecutor executor = new UpdateStatementExecutor(st);
//        Object o = executor.execute(sqlString);
//        Assert.assertNotNull(o);
//    }
}
