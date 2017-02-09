package pl.edu.agh.wp.orm.session.executor.impl;

import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementExecutor extends StatementExecutor{

    public InsertStatementExecutor(Statement statement){
        super(statement);
    }

    public Object execute(String sql) {
        try {

            return statement.execute(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new ORMException("Exception while executing insert query",e);
        }
    }

}
