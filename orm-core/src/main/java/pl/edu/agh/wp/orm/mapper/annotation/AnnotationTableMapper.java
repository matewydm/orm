package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBId;
import pl.ed.agh.wp.orm.annotations.DBOneToMany;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.mapper.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationTableMapper implements TableMapper {


    private ColumnMapper columnMapper;
    private IdMapper idMapper;
    private ManyToOneMapper manyToOneMapper;
    private OneToManyMapper oneToManyMapper;
    private static List<Class<? extends  Annotation>> supportedAnnotation ;

    static{
        supportedAnnotation = new ArrayList<>();
        supportedAnnotation.add(DBColumn.class);
        supportedAnnotation.add(DBId.class);
        supportedAnnotation.add(DBOneToMany.class);
        supportedAnnotation.add(DBOneToMany.class);
    }

    public AnnotationTableMapper(ColumnMapper columnMapper, IdMapper idMapper,ManyToOneMapper manyToOneMapper,OneToManyMapper oneToManyMapper) {
        this.columnMapper = columnMapper;
        this.idMapper = idMapper;
        this.manyToOneMapper = manyToOneMapper;
        this.oneToManyMapper = oneToManyMapper;
    }

    public DBTableObject getTable(Class clazz) {
        DBTable annotation = (DBTable) clazz.getAnnotation(DBTable.class);
        DBTableObject tableObject = new DBTableObject();
        tableObject.setSchemaName(annotation.schema());
        tableObject.setTableName(AnnotationUtils.preparePropertyName(annotation.name(),clazz.getSimpleName()));
        tableObject.setFullName(prepareFullName(tableObject,clazz.getSimpleName()));
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(AnnotationTableMapper::isFieldSuported).collect(Collectors.toList());
        List<Field> manyToOneReference = fields.stream().filter(AnnotationUtils::hasManyToOneAnnotation).collect(Collectors.toList());;
        List<Field> idReference = fields.stream().filter(AnnotationUtils::hasIdAnnotation).collect(Collectors.toList());;
        List<Field> oneToManyReference = fields.stream().filter(AnnotationUtils::hasOneToManyAnnotation).collect(Collectors.toList());;
        List<Field> simplyColumns = getSimpleColumn(fields.stream(),manyToOneReference,oneToManyReference,idReference);
        tableObject.setColumns(columnMapper.getColumns(simplyColumns));
        tableObject.setIdObject(idMapper.getIdObject(idReference));
//        tableObject.setDbOneToManyReferences(oneToManyMapper.getManyToOneReferences(oneToManyReference));
        tableObject.setManyToOneReferences(manyToOneMapper.getManyToOneReferences(manyToOneReference));

        tableObject.setEntity(clazz);

        return tableObject;
    }

    private List<Field> getSimpleColumn(Stream<Field> fields, List<Field> manyToOneReference, List<Field> oneToManyReference, List<Field> idReferejce) {
        return fields.filter(f -> !idReferejce.contains(f)).filter(f -> !manyToOneReference.contains(f)).
                filter(f -> !oneToManyReference.contains(f)).collect(Collectors.toList());
    }


    public static Boolean isFieldSuported(Field field){
        if(field.getAnnotations().length > 0) {
            List<Annotation> annotations = Arrays.asList(field.getAnnotations());
            return Collections.disjoint(annotations, supportedAnnotation);
        }
        else  return false;
    }
    private String prepareFullName(DBTableObject tableObject,String className) {
        String name = tableObject.getSchemaName();
        String schema = tableObject.getTableName();
        if(name.isEmpty()) return className;
        else return schema +"."+name;
    }


}

