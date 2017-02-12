package pl.edu.agh.wp.orm.mapper.annotation;

import org.reflections.ReflectionUtils;
import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBJoinColumn;
import pl.ed.agh.wp.orm.annotations.DBManyToOne;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.dto.DBAbstractReference;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.ManyToOneMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationManyToOneMapper extends AnnotationReferenceMapper implements ManyToOneMapper {

    @Override
    public List<DBManyToOneReference> getManyToOneReferences(List<Field> fields) {
        List<DBManyToOneReference> objects =fields.stream()
                .filter(AnnotationUtils::hasManyToOneAnnotation)
                .map(this::prepareReference).
                        collect(Collectors.toList());
        return objects;
    }

    private DBManyToOneReference prepareReference(Field field) {
        DBManyToOneReference reference = new DBManyToOneReference(field);
        reference.setField(field);
        reference.setConverter(getType(field));
        DBManyToOne manyToOneAnnotation = field.getAnnotation(DBManyToOne.class);
        reference.setFetch(manyToOneAnnotation.fetch());
        if(AnnotationUtils.hasAnnotation(field,DBJoinColumn.class))
            handleJoinColumnAnnotation(reference,field);
        else
            handleDefaultJoinColumn(reference,field);
        handleSimpleColumn(field,reference);
        
        return reference;
    }

    private void handleSimpleColumn(Field field, DBManyToOneReference reference) {
        DBColumn annotation = field.getAnnotation(DBColumn.class);

        if(annotation != null){
            reference.setUnique(annotation.unique());
            reference.setMaxLength(annotation.maxLength());
            reference.setNullable(annotation.nullable());
            reference.setScale(annotation.scale());
            reference.setPrecision(annotation.precision());
        }else {

            try {
                handleDefaultColumnValue(reference);
            } catch (NoSuchMethodException e) {
                throw  new ORMException("Cannot load object");
            }
        }
    }

    private void handleDefaultColumnValue(DBManyToOneReference reference) throws NoSuchMethodException {

        Class clazz = DBColumn.class;
        Method m = clazz.getMethod(nullable);
        reference.setNullable((Boolean) m.getDefaultValue());

        m = clazz.getMethod(maxLength);
        reference.setMaxLength((Integer) m.getDefaultValue());
        m = clazz.getMethod(precision);
        reference.setPrecision((Integer) m.getDefaultValue());

        m = clazz.getMethod(scale);
        reference.setScale((Integer) m.getDefaultValue());

        m = clazz.getMethod(unique);
        reference.setUnique((Boolean) m.getDefaultValue());

    }

    protected void setDefaultJoinedClass(DBAbstractReference reference, Field field) {
        reference.setJoinedClass(field.getType());
    }

    protected void setDefaultTableName(DBAbstractReference reference, Field field) {
        String tableName = field.getType().getSimpleName();
        reference.setJoinTable(tableName);
    }

    protected void setDefaultColumnName(DBAbstractReference reference, Field field) {
        String name = field.getName();
        reference.setColumnName((name+defaultSuffix));
    }
}
