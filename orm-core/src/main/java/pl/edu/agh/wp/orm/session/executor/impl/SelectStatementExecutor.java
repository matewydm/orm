package pl.edu.agh.wp.orm.session.executor.impl;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationColumnMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationIdMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationManyToOneMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationTableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;
import pl.edu.agh.wp.orm.session.executor.StatementExecutor;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectStatementExecutor extends StatementExecutor{

    public SelectStatementExecutor(Statement statement) {
        super(statement);
    }

    public ResultSet execute(String sqlQuery) {
        try {
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new ORMException("Exception while executing select query");
        }
    }

}
