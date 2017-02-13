package pl.edu.agh.wp.orm.mapper.annotation;

import org.reflections.ReflectionUtils;
import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBJoinColumn;
import pl.ed.agh.wp.orm.annotations.DBOneToMany;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.dto.DBAbstractReference;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.dto.DBOneToManyReference;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.OneToManyMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationOneToManyMapper extends AnnotationReferenceMapper implements OneToManyMapper {

    @Override
    public List<DBOneToManyReference> getOneToManyReferences (List<Field> fields) {
        List<DBOneToManyReference> objects =fields.stream()
                .filter(AnnotationUtils::hasOneToManyAnnotation)
                .map(this::prepareReference).
                        collect(Collectors.toList());
        return objects;
    }

    private DBOneToManyReference prepareReference(Field field) {
        DBOneToManyReference reference = new DBOneToManyReference(field);
        DBOneToMany annotation = field.getAnnotation(DBOneToMany.class);
        reference.setFetch(annotation.fetch());

        if(AnnotationUtils.hasAnnotation(field,DBJoinColumn.class))
            handleJoinColumnAnnotation(reference,field);
        else
            handleDefaultJoinColumn(reference,field);

        setDefaultJoinedClass(reference,field);

        return reference;
    }

    protected void setDefaultTableName(DBAbstractReference reference, Field field) {
        String tableName = getGenericType(field).getSimpleName();
        reference.setJoinTable(tableName);
    }

    protected void setDefaultJoinedClass(DBAbstractReference reference, Field field) {
        Class genericClass = getGenericType(field);
        reference.setJoinedClass(genericClass);
    }

    private Class getGenericType(Field field) {
        ParameterizedType fieldType = (ParameterizedType) field.getGenericType();
        return (Class<?>) fieldType.getActualTypeArguments()[0];
    }

    protected void setDefaultColumnName(DBAbstractReference reference, Field field) {
        String name = field.getDeclaringClass().getSimpleName();
        reference.setColumnName((name+defaultSuffix));
    }

}
