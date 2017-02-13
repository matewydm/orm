package pl.edu.agh.wp.orm.session.executor.impl;


import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateStatementExecutor extends StatementExecutor{

    public UpdateStatementExecutor(Statement statement) {
        super(statement);
    }

    public ResultSet execute(String sqlQuery) {
        try {
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new ORMException("Exception while executing update query");
        }
    }
}
