package pl.edu.agh.wp.orm.creator;


import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCreator{

    public void createDatabase(){
        Configuration configuration = new Configuration()
                .addDriverClass("org.postgresql.Driver")
                .addDatabaseUrl("jdbc:postgresql://localhost:5432/postgres")
                .addUser("postgres")
                .addPassword("dare")
                .setAnnotationParsing(true)
                .addScannedPackage("pl.edu.agh");


        configuration.buildSessionFactory();
        List<DBQuery> queryList = new ArrayList<>();

        for (DBTableObject object : EntitiesRepository.getInstance()){
            QueryCreator queryCreator = new CreateQueryCreator(object);
            DBQuery query = queryCreator.createQuery(null);
            query.getSQLQuery();
            queryList.add(query);
        }
    }

    }

