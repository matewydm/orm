package pl.edu.agh.wp.orm.session.executor.impl;


import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatementExecutor extends StatementExecutor{

    public CreateStatementExecutor(Statement statement){
        super(statement);
    }

    public Object execute(String sql) {
        try {
            return statement.execute(sql);

        } catch (SQLException e) {
            throw new ORMException("Exception while executing create query",e);
        }
    }
}
