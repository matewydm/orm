package pl.edu.agh.wp.orm.session.executor.impl;

import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementExecutor extends StatementExecutor{

    public InsertStatementExecutor(Statement statement){
        super(statement);
    }

    public Object execute(String sql) {
        try {

             statement.execute(sql,Statement.RETURN_GENERATED_KEYS);
             ResultSet rs = statement.getGeneratedKeys();
             if(rs.next())
                 return rs.getObject(1);
            return null;
        } catch (SQLException e) {
            throw new ORMException("Exception while executing insert query",e);
        }
    }

}
