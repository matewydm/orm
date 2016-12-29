package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.mapper.ColumnMapper;
import pl.edu.agh.wp.orm.mapper.TableMapper;

import java.lang.annotation.Annotation;
import java.util.List;

public class AnnotationTableMapper implements TableMapper {

    ColumnMapper mapper;

    public DBTableObject getTable(Class clazz) {
        DBTable annotation = (DBTable) clazz.getAnnotation(DBTable.class);
        DBTableObject tableObject = new DBTableObject();
        tableObject.setSchemaName(annotation.schema());
        tableObject.setTableName(AnnotationUtils.preparePropertyName(annotation.name(),clazz.getSimpleName()));
        tableObject.setFullName(prepareFullName(tableObject,clazz.getSimpleName()));
        tableObject.setColumns(mapper.getColumns(clazz));

        return tableObject;
    }

    @Override
    public void setColumnMapper(ColumnMapper columnMapper) {
        mapper = columnMapper;
    }

    private String prepareFullName(DBTableObject tableObject,String className) {
        String name = tableObject.getSchemaName();
        String schema = tableObject.getTableName();
        if(name.isEmpty()) return className.toUpperCase();
        else return schema +"."+name;
    }

}

