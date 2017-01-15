package pl.edu.agh.wp.orm.session;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.edu.agh.wp.orm.dto.DBColumnObject;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationColumnMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationIdMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationManyToOneMapper;
import pl.edu.agh.wp.orm.mapper.annotation.AnnotationTableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectStatementExecutor  {

    private final Statement statement;
    private DBTableObject table;
    private Class clazz;
    private ORMFactory factory = new AnnotationORMFactory();
    public SelectStatementExecutor(Statement statement,Class clazz){
        TableMapper mapper = factory.getMapper();
        this.table = mapper.getTable(clazz);
        this.statement = statement;
        this.clazz = clazz;
    }

    public List execute(String sqlQuery) {
        ResultSet resultSet;
        List<Object> outputList = new ArrayList<>();

        try {
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Field[] columnFields = clazz.getDeclaredFields();

                Object obj = clazz.newInstance();
                for (DBColumnObject column : table.getColumns()) {

                    Object columnValue = resultSet.getObject(column.getColumnName());

                    for (Field columnField : columnFields) {
                        if (columnField.isAnnotationPresent(DBColumn.class)) {
                            String columnName = columnField.getAnnotation(DBColumn.class).name();
                            if (column.getColumnName().equalsIgnoreCase(columnName) && columnValue != null) {

                                Field newColumnField = obj.getClass().getDeclaredField(columnField.getName());
                                newColumnField.setAccessible(true);
                                newColumnField.set(obj, columnValue);
                                break;
                            }
                        }
                    }
                }
                outputList.add(obj);
            }
        }
        catch (SQLException | NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return outputList;
    }
}
