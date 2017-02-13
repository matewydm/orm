package pl.edu.agh.wp.orm.session;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.example.Address;
import pl.edu.agh.wp.orm.example.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveTest {

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

    @Ignore
    public void saveTest1() throws Exception {
        List<Address> addressList = new ArrayList<>();
        Person person = new Person();
        person.setAge(2);
        person.setDate(new Date());
        person.setFirstname("Dare");
        person.setLastname("Nie");

        addressList.add(new Address());
        addressList.add(new Address());
//        person.setAddress(addressList);

        session.save(person);

    }

}