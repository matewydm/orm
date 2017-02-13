package pl.edu.agh.wp.orm.session.executor;

import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

import java.sql.PreparedStatement;
import java.sql.Statement;

public abstract class StatementExecutor<T extends Object> {

    protected final Statement statement;

    public StatementExecutor(Statement statement){

        this.statement = statement;

    }
    public abstract T execute(String sqlString);
}
