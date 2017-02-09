package pl.edu.agh.wp.orm.session.executor;

import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

import java.sql.Statement;

public abstract class StatementExecutor {

    protected final Statement statement;
    protected DBTableObject table;
    protected Class clazz;
    protected ORMFactory factory = new AnnotationORMFactory();

    public StatementExecutor(Statement statement,Class clazz){
        TableMapper mapper = factory.getMapper();
        this.table = mapper.getTable(clazz);
        this.statement = statement;
        this.clazz = clazz;
    }
}
