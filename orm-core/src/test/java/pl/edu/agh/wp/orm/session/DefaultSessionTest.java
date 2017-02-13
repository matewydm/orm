package pl.edu.agh.wp.orm.session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.example.Person;
import pl.edu.agh.wp.orm.example.pancakes.Artykuly;
import pl.edu.agh.wp.orm.example.pancakes.Klienci;
import pl.edu.agh.wp.orm.example.pancakes.Zamowienia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void simplySaveTest2() throws Exception {
        DefaultSession session = (DefaultSession) factory.openSession();
        Address a = new Address();

        DBTableObject dbTable = EntitiesRepository.getInstance().getTable(a.getClass());

        session.simplySave(a,dbTable);

        Assert.assertNotNull(a.getId());
    }

    @Test
    public void saveTest() throws Exception {
        Session session =factory.openSession();
        Klienci k = new Klienci(1,"Janek Kowalski","ul","stw","12-120","78428644");
        Zamowienia zamowienia = new Zamowienia(1,k,new Date());
        Artykuly a =new Artykuly(10,12);
        List<Artykuly> articles = new ArrayList<>();

        articles.add(a);
        zamowienia.setArtykuly(articles);
        a.setZamowienia(zamowienia);
        session.save(zamowienia);


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