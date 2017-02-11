package pl.edu.agh.wp.orm.session;

import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;
import pl.edu.agh.wp.orm.creator.QueryCreator;
import pl.edu.agh.wp.orm.creator.SelectQueryCreator;
import pl.edu.agh.wp.orm.criterion.Restrictions;
import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBIdObject;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.impl.SelectStatementExecutor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper{

    private final Connection connection;

    public ResultSetMapper(Connection connection) {
        this.connection = connection;
    }

    public List listResultSet(Class entityClass, String sqlQuery) {
        List<Object> entityList = new ArrayList<>();
        ResultSet resultSet = new SelectStatementExecutor(getStatement()).execute(sqlQuery);
        while (nextResultSet(resultSet)) {
            entityList.add(processRecord(entityClass,resultSet));
        }
        return entityList;
    }

    public Object uniqueResultSet(Class entityClass, String sqlQuery) {
        ResultSet resultSet = new SelectStatementExecutor(getStatement()).execute(sqlQuery);
        nextResultSet(resultSet);
        return processRecord(entityClass,resultSet);
    }

    private Object processRecord(Class entityClass, ResultSet resultSet) {

        DBTableObject tableObject = EntitiesRepository.getInstance().getTable(entityClass);
        Object entity = tableObject.getPersistedInstance();

        DBIdObject dbId = tableObject.getIdObject();
        Object id = getObject(resultSet,dbId.getColumnName());
        dbId.setGeneretedId(entity, id);

        for (DBColumnObject dbColumn : tableObject.getColumns()) {
            Object column = getObject(resultSet,dbColumn.getColumnName());
            dbColumn.setValue(entity, column);
        }

        for (DBManyToOneReference dbManyToOne : tableObject.getManyToOneReferences()) {
            if (dbManyToOne.getFetch().equals(DBFetchType.EAGER)) {
                Object manyToOne = getObject(resultSet,dbManyToOne.getColumnName());

                QueryCreator queryCreator = new SelectQueryCreator(EntitiesRepository.getInstance().getTable(dbManyToOne.getJointedClass()));
                List<Criterion> idCriterion = new ArrayList<>();
                idCriterion.add(Restrictions.eq(dbManyToOne.getColumnName(), manyToOne));
                String sqlJoinedQuery = queryCreator.createQuery(idCriterion).getSQLQuery();
                Object joinedEntity = uniqueResultSet(dbManyToOne.getJointedClass(), sqlJoinedQuery);

                dbManyToOne.setValue(entity, joinedEntity);
            }
        }

        return entity;
    }

    private Object getObject(ResultSet resultSet,String columnLabel) {
        try {
            return resultSet.getObject(columnLabel);
        } catch (SQLException e) {
            throw new ORMException("Cannot get object from resultSet", e);
        }
    }

    private boolean nextResultSet(ResultSet resultSet) {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new ORMException("Cannot find next resultSet",e);
        }
    }

    private Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new ORMException("Can't create a statement");
        }
    }
}
