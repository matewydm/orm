package pl.edu.agh.wp.orm.mapper.annotation;

import org.reflections.ReflectionUtils;
import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBJoinColumn;
import pl.ed.agh.wp.orm.annotations.DBManyToOne;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.dto.DBManyToOneReference;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.ManyToOneMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationManyToOneMapper extends TypeMatcher implements ManyToOneMapper {

    private static final String defaultSuffix ="_ID";
    public static final String nullable ="nullable";
    public static final  String maxLength ="maxLength";
    public static final String unique = "unique";
    public static final String scale ="scale";
    public static final String precision="precision";

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

    @Override
    protected TypeConverter getType(Field field) {
      Field id =  ReflectionUtils.getAllFields(field.getType()).stream().filter(AnnotationUtils::hasIdAnnotation).findAny().get();
        return super.getType(id);
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

        m= clazz.getMethod(maxLength);
        reference.setMaxLength((Integer) m.getDefaultValue());
        m = clazz.getMethod(precision);
        reference.setPrecision((Integer) m.getDefaultValue());

        m = clazz.getMethod(scale);
        reference.setScale((Integer) m.getDefaultValue());

        m = clazz.getMethod(unique);
        reference.setUnique((Boolean) m.getDefaultValue());

    }

    private void handleJoinColumnAnnotation(DBManyToOneReference reference, Field field) {
        DBJoinColumn joinColumnAnnotation = field.getAnnotation(DBJoinColumn.class);
        String columnName = joinColumnAnnotation.columnName();
        String tableName = joinColumnAnnotation.tableName();
        if(columnName.isEmpty())
           setDefaultColumnName(reference,field);
        else reference.setColumnName(columnName);

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
        reference.setColumnName((name+defaultSuffix));
    }
}
