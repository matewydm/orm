package pl.edu.agh.wp.orm.session.executor.impl;

import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStatementExecutor extends StatementExecutor {

    public DeleteStatementExecutor(Statement statement) { super(statement); }

    @Override
    public Object execute(String sql) {
        try {
            statement.execute(sql,Statement.SUCCESS_NO_INFO);
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
                return rs.getObject(1);
            return null;
        } catch (SQLException e) {
            throw new ORMException("Exception while executing delete query",e);
        }
    }
}
