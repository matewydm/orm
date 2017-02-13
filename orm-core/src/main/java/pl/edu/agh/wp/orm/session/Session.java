package pl.edu.agh.wp.orm.session;

import pl.edu.agh.wp.orm.criterion.Criteria;
import pl.edu.agh.wp.orm.criterion.CriteriaImpl;
import pl.edu.agh.wp.orm.exception.ORMException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public interface Session {

    void save(Object object) throws ORMException;

    void update(Object object) throws ORMException;

    Object get(Object id, Class clazz);

    void delete (Object object) throws ORMException;

    boolean isOpened();

    void close();

    Criteria createCriteria(Class clazz);

    Statement getStatement();

    Connection getConnection();
}
