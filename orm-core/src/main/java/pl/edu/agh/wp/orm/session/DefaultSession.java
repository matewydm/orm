package pl.edu.agh.wp.orm.session;

import org.apache.log4j.Logger;
import pl.edu.agh.wp.orm.creator.*;
import pl.edu.agh.wp.orm.criterion.Criteria;
import pl.edu.agh.wp.orm.criterion.CriteriaImpl;
import pl.edu.agh.wp.orm.criterion.Restrictions;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.exception.ORMNoSuchRecordException;
import pl.edu.agh.wp.orm.exception.ORMNoSuchTableException;
import pl.edu.agh.wp.orm.session.executor.impl.CreateStatementExecutor;
import pl.edu.agh.wp.orm.session.executor.impl.DeleteStatementExecutor;
import pl.edu.agh.wp.orm.session.executor.impl.InsertStatementExecutor;
import pl.edu.agh.wp.orm.session.executor.impl.UpdateStatementExecutor;

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
//            reference.get
        }
    }



    @Override
    public void update(Object object) {
        EntitiesRepository repository = EntitiesRepository.getInstance();
        DBTableObject table = repository.getTable(object.getClass());
        executeUpdate(table,object);

    }

    @Override
    public void create(Object object){
        EntitiesRepository repository = EntitiesRepository.getInstance();
        DBTableObject table = repository.getTable(object.getClass());
        executeTableCreation(table,object);
    }


    @Override
    public Object get(Object id, Class clazz) {
        DBTableObject table = entitiesInformation.getTable(clazz);
        return createCriteria(clazz)
                    .add( Restrictions.eq( table.getIdObject().getColumnName(),id ))
                    .uniqueResult();
    }

    @Override
    public void delete(Object object) throws ORMException{
        EntitiesRepository repository = EntitiesRepository.getInstance();
        DBTableObject table = repository.getTable(object.getClass());
        executeDeletion(table, object);
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
        return new CriteriaImpl(connection,clazz);
    }

     void simplySave(Object o, DBTableObject dbTableObject) {
        QueryCreator queryCreator =  new InsertQueryCreator(dbTableObject);
        DBQuery query = queryCreator.createQuery(o);
        InsertStatementExecutor insertExecutor =
                new InsertStatementExecutor(getStatement());
        Object id = insertExecutor.execute(query.getSQLQuery());

        if(id != null)
            dbTableObject.getIdObject().setGeneretedId(o,id);


    }

    public void executeTableCreation(DBTableObject table, Object object) throws ORMException {
        if(table != null){
            QueryCreator queryCreator = new CreateQueryCreator(table);
            DBQuery query = queryCreator.createQuery(object);
            CreateStatementExecutor createExecutor = new CreateStatementExecutor(getStatement());
            Object id = createExecutor.execute(query.getSQLQuery());
            if(id == null)
                throw new ORMNoSuchRecordException("There is no such record in database");
        }
        else {
            throw new ORMNoSuchTableException("Given class has no table in database");
        }
    }


    public Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            throw new ORMException("Cannot create connection",e);
        }
    }

    private void executeDeletion(DBTableObject table, Object object) throws ORMException {
        if (table != null) {
            QueryCreator queryCreator =  new DeleteQueryCreator(table);
            DBQuery query = queryCreator.createQuery(object);
            DeleteStatementExecutor deleteExecutor = new DeleteStatementExecutor(getStatement());
            Object id = deleteExecutor.execute(query.getSQLQuery());
            if(id == null)
                throw new ORMNoSuchRecordException("There is no such record in database");
        }
        else {
            throw new ORMNoSuchTableException("Given class has no table in database");
        }
    }

    private void executeUpdate(DBTableObject table, Object object) throws ORMException{
        if (table != null) {
            QueryCreator queryCreator =  new UpdateQueryCreator(table);
            DBQuery query = queryCreator.createQuery(object);
            UpdateStatementExecutor updateExecutor = new UpdateStatementExecutor(getStatement());
            Object id = updateExecutor.execute(query.getSQLQuery());
            if(id == null)
                throw new ORMNoSuchRecordException("There is no such record in database");
        }
        else {
            throw new ORMNoSuchTableException("Given class has no table in database");
        }
    }


    @Override
    public Connection getConnection(){
        return connection;
    }
}
