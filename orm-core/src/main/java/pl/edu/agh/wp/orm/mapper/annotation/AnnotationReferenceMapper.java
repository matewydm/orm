package pl.edu.agh.wp.orm.mapper.annotation;

import org.reflections.ReflectionUtils;
import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBJoinColumn;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.dto.DBAbstractReference;
import pl.edu.agh.wp.orm.dto.DBOneToManyReference;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by mucha on 12.02.2017.
 */
public abstract class AnnotationReferenceMapper extends TypeMatcher {

    protected static final String defaultSuffix ="_ID";
    public static final String nullable ="nullable";
    public static final  String maxLength ="maxLength";
    public static final String unique = "unique";
    public static final String scale ="scale";
    public static final String precision="precision";

    @Override
    protected TypeConverter getType(Field field) {
        Field id =  ReflectionUtils.getAllFields(field.getType()).stream().filter(AnnotationUtils::hasIdAnnotation).findAny().get();
        return super.getType(id);
    }

    protected void handleJoinColumnAnnotation(DBAbstractReference reference, Field field) {
        DBJoinColumn joinColumnAnnotation = field.getAnnotation(DBJoinColumn.class);
        String columnName = joinColumnAnnotation.columnName();
        String tableName = joinColumnAnnotation.tableName();
        if(columnName.isEmpty())
            setDefaultColumnName(reference,field);
        else
            reference.setColumnName(columnName);

        if(tableName.isEmpty())
            setDefaultTableName(reference,field);
        else
            reference.setJoinTable(tableName);

        setDefaultJoinedClass(reference,field);
    }

    protected void handleDefaultJoinColumn(DBAbstractReference reference, Field field) {
        setDefaultColumnName(reference,field);
        setDefaultTableName(reference,field);
        setDefaultJoinedClass(reference,field);
    }

    protected abstract void setDefaultColumnName(DBAbstractReference reference, Field field);

    protected abstract void setDefaultJoinedClass(DBAbstractReference reference, Field field);

    protected abstract void setDefaultTableName(DBAbstractReference reference, Field field);

}
