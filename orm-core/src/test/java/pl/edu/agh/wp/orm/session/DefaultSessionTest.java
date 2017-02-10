package pl.edu.agh.wp.orm.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.example.Person;

import static org.junit.Assert.*;

public class DefaultSessionTest {
    SessionFactory factory;
    @Test
    public void simplySaveTest() throws Exception {
        DefaultSession session = (DefaultSession) factory.openSession();
        Person p = new Person();
        DBTableObject dbTable = EntitiesRepository.getInstance().getTable(p.getClass());

        session.simplySave(p,dbTable);

        Assert.assertNotNull(p.getId());
    }

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

}