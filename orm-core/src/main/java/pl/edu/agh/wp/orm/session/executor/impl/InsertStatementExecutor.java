package pl.edu.agh.wp.orm.session.executor.impl;

import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementExecutor extends StatementExecutor{

    public InsertStatementExecutor(Statement statement,Class clazz){
        super(statement,clazz);
    }

    public void execute(String sql) {
        try {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new ORMException("Exception while executing insert query",e);
        }
    }

}
