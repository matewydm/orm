package pl.edu.agh.wp.orm.session;

import org.apache.log4j.Logger;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.creator.InsertQueryCreator;
import pl.edu.agh.wp.orm.creator.QueryCreator;
import pl.edu.agh.wp.orm.criterion.Criteria;
import pl.edu.agh.wp.orm.criterion.CriteriaImpl;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.sql.Connection;
import java.sql.SQLException;
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
        List<DBManyToOneReference> reference = table.getManyToOneReferences();
    }



    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(Object object) {

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
            logger.error(e);
            throw new ORMException("Exception",e);
        }

    }

    private void simplySave(Object o,DBTableObject dbTableObject) {
        QueryCreator queryCreator =  new InsertQueryCreator(dbTableObject);
        DBQuery query = queryCreator.createQuery(o);

    }

}
