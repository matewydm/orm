package pl.edu.agh.wp.orm.session;

import pl.edu.agh.wp.orm.criterion.Criteria;
import pl.edu.agh.wp.orm.criterion.CriteriaImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public interface Session {

    void save(Object object);

    void update(Object object);

    void delete (Object object);

    boolean isOpened();

    void close();

    Criteria createCriteria(Class clazz);

    Statement getStatement();

    PreparedStatement preparedStatement(String sql, int type);

    Connection getConnection();
}
