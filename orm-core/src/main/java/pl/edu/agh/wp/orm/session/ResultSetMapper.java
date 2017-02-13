package pl.edu.agh.wp.orm.session;

import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;
import pl.edu.agh.wp.orm.creator.QueryCreator;
import pl.edu.agh.wp.orm.creator.SelectQueryCreator;
import pl.edu.agh.wp.orm.criterion.Restrictions;
import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.dto.*;
import pl.edu.agh.wp.orm.dto.repo.EntitiesRepository;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationReferenceMapper;
import pl.edu.agh.wp.orm.session.executor.impl.SelectStatementExecutor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ResultSetMapper{

    ConcurrentLinkedQueue<Class> joinedClasses = new ConcurrentLinkedQueue<>();
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

        joinedClasses.add(entityClass);

        DBTableObject tableObject = EntitiesRepository.getInstance().getTable(entityClass);
        Object entity = tableObject.getPersistedInstance();

        DBIdObject dbId = tableObject.getIdObject();
        Object id = getObject(resultSet,dbId.getColumnName());
        dbId.setGeneretedId(entity, id);
        dbId.addPersistedId(id);

        for (DBColumnObject dbColumn : tableObject.getColumns()) {
            Object column = getObject(resultSet,dbColumn.getColumnName());
            dbColumn.setValue(entity, column);
        }

        for (DBManyToOneReference dbManyToOne : tableObject.getManyToOneReferences()) {
            if (dbManyToOne.isEager() && !isAlreadyJoined(dbManyToOne)) {
                Object manyToOne = getObject(resultSet,dbManyToOne.getColumnName());

                String sqlJoinedQuery = selectSqlQuery(dbManyToOne,manyToOne);
                Object joinedEntity = uniqueResultSet(dbManyToOne.getJoinedClass(), sqlJoinedQuery);

                dbManyToOne.setValue(entity, joinedEntity);
            }
        }

        for (DBOneToManyReference dbOneToMany : tableObject.getOneToManyReference()) {
            if (dbOneToMany.isEager() && !isAlreadyJoined(dbOneToMany)) {

                String sqlJoinedQuery = selectSqlQuery(dbOneToMany,id);
                List joinedEntities = listResultSet(dbOneToMany.getJoinedClass(), sqlJoinedQuery);
                dbOneToMany.setValue(entity, joinedEntities);
            }
        }

        return entity;
    }

    private String selectSqlQuery(DBAbstractReference reference, Object idValue) {
        QueryCreator queryCreator = new SelectQueryCreator(EntitiesRepository.getInstance().getTable(reference.getJoinedClass()));
        List<Criterion> idCriterion = new ArrayList<>();
        idCriterion.add(Restrictions.eq(reference.getColumnName(), idValue));
        return queryCreator.createQuery(idCriterion).getSQLQuery();
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

    private boolean isAlreadyJoined(DBAbstractReference reference) {
        return joinedClasses.contains(reference.getJoinedClass());
    }

    private Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new ORMException("Can't create a statement");
        }
    }
}
