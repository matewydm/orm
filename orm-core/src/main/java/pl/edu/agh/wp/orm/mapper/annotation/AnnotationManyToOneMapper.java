package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.DBJoinColumn;
import pl.ed.agh.wp.orm.annotations.DBManyToOne;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.mapper.ManyToOneMapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationManyToOneMapper implements ManyToOneMapper {

    private static final String defaultSuffix ="_ID";

    @Override
    public List<DBManyToOneReference> getManyToOneReferences(List<Field> fields) {
        List<DBManyToOneReference> objects =fields.stream()
                .filter(AnnotationUtils::hasManyToOneAnnotation)
                .map(this::prepareReference).
                        collect(Collectors.toList());
        return objects;
    }

    private DBManyToOneReference prepareReference(Field field) {
        DBManyToOneReference reference = new DBManyToOneReference();
        DBManyToOne manyToOneAnnotation = field.getAnnotation(DBManyToOne.class);
        reference.setFetch(manyToOneAnnotation.fetch());


        if(AnnotationUtils.hasAnnotation(field,DBJoinColumn.class))
            handleJoinColumnAnnotation(reference,field);
        else
            handleDefaultJoinColumn(reference,field);
        
        
        return reference;
    }

    private void handleJoinColumnAnnotation(DBManyToOneReference reference, Field field) {
        DBJoinColumn joinColumnAnnotation = field.getAnnotation(DBJoinColumn.class);
        String columnName = joinColumnAnnotation.columnName();
        String tableName = joinColumnAnnotation.tableName();
        if(columnName.isEmpty())
           setDefaultColumnName(reference,field);
        else reference.setJoinColumn(columnName);

        if(tableName.isEmpty())
            setDefaultTableName(reference,field);
        else reference.setJoinTable(tableName);

        setDefaultJoinedClass(reference,field);




    }

    private void handleDefaultJoinColumn(DBManyToOneReference reference, Field field) {
        setDefaultColumnName(reference,field);
        setDefaultTableName(reference,field);
        setDefaultJoinedClass(reference,field);


    }

    private void setDefaultJoinedClass(DBManyToOneReference reference, Field field) {
        reference.setJointedClass(field.getType());
    }

    private void setDefaultTableName(DBManyToOneReference reference, Field field) {
        String tableName = field.getType().getSimpleName();
        reference.setJoinTable(tableName);
    }

    private void setDefaultColumnName(DBManyToOneReference reference, Field field) {
        String name = field.getName();
        reference.setJoinColumn((name+defaultSuffix));
    }
}
