package pl.edu.agh.wp.orm.session.executor.impl;

import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertStatementExecutor extends StatementExecutor{

    public InsertStatementExecutor(Statement statement,Class clazz){
        super(statement,clazz);
    }

}
