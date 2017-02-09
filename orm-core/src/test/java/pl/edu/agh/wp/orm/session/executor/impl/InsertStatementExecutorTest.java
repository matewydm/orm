package pl.edu.agh.wp.orm.session.executor.impl;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.session.Session;
import pl.edu.agh.wp.orm.session.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static org.junit.Assert.*;

public class InsertStatementExecutorTest {

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
        String sqlString = "INSERT INTO Person (name,lastname,age,birth_date) VALUES ('abba','a',20,'2017-02-09');";
        Connection connection = factory.openSession().getConnection();
        PreparedStatement st = connection.prepareStatement(sqlString,Statement.RETURN_GENERATED_KEYS);
        st.executeQuery();
       // InsertStatementExecutor executor = new InsertStatementExecutor(statement);
      //  executor.execute(sqlString);
    }

}