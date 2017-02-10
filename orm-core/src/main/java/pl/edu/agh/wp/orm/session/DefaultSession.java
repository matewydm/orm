package pl.edu.agh.wp.orm.session;

import org.apache.log4j.Logger;
import pl.edu.agh.wp.orm.creator.DeleteQueryCreator;
import pl.edu.agh.wp.orm.creator.InsertQueryCreator;
import pl.edu.agh.wp.orm.creator.QueryCreator;
import pl.edu.agh.wp.orm.criterion.Criteria;
import pl.edu.agh.wp.orm.criterion.CriteriaImpl;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.impl.DeleteStatementExecutor;
import pl.edu.agh.wp.orm.session.executor.impl.InsertStatementExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DefaultSession implements Session {

    private EntitiesRepository entitiesInformation;
    private Connection connection;
    Logger logger = Logger.getLogger(DefaultSession.class);

    public DefaultSession(EntitiesRepository entitiesInformation, Connection connection) {
        this.entitiesInformation = entitiesInformation;
        this.connection = connection;
    }

    @Override
    public void save(Object object) {
        EntitiesRepository repository = EntitiesRepository.getInstance();
        DBTableObject table = repository.getTable(object.getClass());
        List<DBManyToOneReference> referenceList = table.getManyToOneReferences();
        for (DBManyToOneReference reference : referenceList) {

        }
    }



    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(Object object) {
        EntitiesRepository repository = EntitiesRepository.getInstance();
        DBTableObject table = repository.getTable(object.getClass());
        if (table != null) {
            try {
                QueryCreator queryCreator =  new DeleteQueryCreator(table);
                DBQuery query = queryCreator.createQuery(object);
                DeleteStatementExecutor deleteExecutor =
                        new DeleteStatementExecutor(getStatement());
                Object id = deleteExecutor.execute(query.getSQLQuery());

                if(id == null)
                    throw new ORMException("Object id was not found");
            } catch (Exception e) {
                throw e;
            }
        }
        else {
            throw new ORMException("Given class has no table in repository");
        }


    }

    @Override
    public boolean isOpened() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public Criteria createCriteria(Class clazz) {
        try {
            return new CriteriaImpl(connection.createStatement(),clazz);
        } catch (SQLException e) {
            throw new ORMException("",e);
        }


    }

    private void simplySave(Object o, DBTableObject dbTableObject) {
        QueryCreator queryCreator =  new InsertQueryCreator(dbTableObject);
        DBQuery query = queryCreator.createQuery(o);
        InsertStatementExecutor insertExecutor =
                new InsertStatementExecutor(getStatement());
        Object id = insertExecutor.execute(query.getSQLQuery());

        if(id != null)
            dbTableObject.getIdObject().setGeneretedId(o,id);


    }

    public Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new ORMException("Cannot create connection",e);
        }
    }


    @Override
    public Connection getConnection(){
        return connection;
    }
}
