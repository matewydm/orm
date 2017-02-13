package pl.edu.agh.wp.orm.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.example.pancakes.Klienci;
import pl.edu.agh.wp.orm.example.pancakes.Zamowienia;

public class GetTest {

    Session session;

    @Before
    public void setUp() throws Exception {
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        session = configuration.buildSessionFactory().openSession();
    }

    @Test
    public void getTest1() throws Exception {
        Object expectedId = 1;
        Zamowienia zamowienia = (Zamowienia) session.get(expectedId,Zamowienia.class);
    }

    @Test
    public void getTest2() throws Exception {
        Object expectedId = 1;
        Klienci klienci = (Klienci) session.get(expectedId,Klienci.class);
    }
}
