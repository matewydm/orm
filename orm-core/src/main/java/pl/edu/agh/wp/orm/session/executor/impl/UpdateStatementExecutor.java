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

    public Object execute(String sqlQuery) {
        try {
            statement.execute(sqlQuery,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
                return rs.getObject(1);
            return null;
        } catch (SQLException e) {
            throw new ORMException("Exception while executing update query",e);
        }
    }
}
