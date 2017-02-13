package pl.edu.agh.wp.orm.creator;


import pl.ed.agh.wp.orm.annotations.DBManyToOne;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.configuration.Configuration;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.Session;
import pl.edu.agh.wp.orm.session.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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


      SessionFactory factory = configuration.buildSessionFactory();
       List<DBQuery> list =  getQueries();
        try {
            create(factory, list);
        } catch (SQLException e) {
            throw new ORMException(e);
        }


    }

    private void create(SessionFactory factory, List<DBQuery> list) throws SQLException {
        Session s =factory.openSession();
        Connection c =s.getConnection();
        c.setAutoCommit(false);
        for(DBQuery query : list){
            Statement stm = c.createStatement();
            stm.execute(query.getSQLQuery());
        }

        c.commit();
    }

    private List<DBQuery> getQueries() {
        List<DBQuery> queryList = new ArrayList<>();
        List<DBQuery> alterList = new ArrayList<>();
        for (DBTableObject object : EntitiesRepository.getInstance()){
            QueryCreator queryCreator = new CreateQueryCreator(object);
            DBQuery query = queryCreator.createQuery(null);
            queryList.add(query);
            for(DBManyToOneReference ref: object.getManyToOneReferences()) {
                QueryCreator alterCreator = new AlterQueryCreator(object,ref);
                alterList.add(alterCreator.createQuery(null));
            }

        }
        queryList.addAll(alterList);
        return queryList;
    }

}

