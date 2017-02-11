package pl.edu.agh.wp.orm.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.dto.DBIdObject;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.session.Session;
import pl.edu.agh.wp.orm.session.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    /*public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext
//                ("bean.xml");
//        context.getApplicationName();
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");

        SessionFactory session = configuration.buildSessionFactory();
        List<DBQuery> lsit = new ArrayList<>();
        EntitiesRepository repo = EntitiesRepository.getInstance();
        for(DBTableObject table : repo){
            getSQl
                    list.add(quer);
        }

        for(DBTableObject tableObject:repo){
            ALterFoer
                    ALTER TABLE table.getTAbleNem + add FOREGIN key()
                    refere nes
        }

    }
    */
}






