package pl.edu.agh.wp.orm.criterion;

import pl.edu.agh.wp.orm.creator.QueryCreator;
import pl.edu.agh.wp.orm.creator.SelectQueryCreator;
import pl.edu.agh.wp.orm.criterion.queries.AbstractCriterion;
import pl.edu.agh.wp.orm.criterion.queries.Criterion;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.dto.queries.DBQuery;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;
import pl.edu.agh.wp.orm.session.SelectStatementExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriteriaImpl implements Criteria {

    private final Statement statement;
    private Class clazz;
    private List<Criterion> criterionList;
    private DBTableObject table;
    private Criterion limitQuery;
    private ORMFactory factory = new AnnotationORMFactory();
    public CriteriaImpl(Statement statement, Class clazz) {
        TableMapper mapper = factory.getMapper();
        this.table = mapper.getTable(clazz);
        this.statement = statement;
        this.clazz = clazz;
        this.criterionList = new ArrayList<>();
    }

    @Override
    public CriteriaImpl add(Criterion criterion) {
        criterionList.add(criterion);
        return this;
    }

    @Override
    public Criteria setMaxResult(Integer maxResult) {
        this.limitQuery = new AbstractCriterion() {
            @Override
            public void buildQuery() {
                appendWithSpace(DatabaseStatement.LIMIT);
                appendWithSpace(maxResult.toString());
            }
        };
        return this;
    }
    @Override
    public DBQuery build(){
        QueryCreator queryCreator = new SelectQueryCreator(table);
        return queryCreator.createQuery(criterionList);
    }

    @Override
    public List list() {
        DBQuery query = build();
        String sqlQuery = query.getSQLQuery();
        return new SelectStatementExecutor(statement,clazz).execute(sqlQuery);
    }

    public List<Criterion> getCriterionList() {
        return criterionList;
    }

}
