package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBGeneratedValue;
import pl.ed.agh.wp.orm.annotations.enums.GenerationType;
import pl.edu.agh.wp.orm.annotations.utilis.AnnotationUtils;
import pl.edu.agh.wp.orm.converter.DefaultTypeRegister;
import pl.edu.agh.wp.orm.converter.TypeRegister;
import pl.edu.agh.wp.orm.dto.DBIdObject;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.IdMapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationIdMapper implements IdMapper {
    TypeRegister register = new DefaultTypeRegister();

    @Override
    public DBIdObject getIdObject(List<Field> fields) {
        List<DBIdObject> objects =fields.stream()
                .filter(AnnotationUtils::hasIdAnnotation).map(this::prepareIdColumn).collect(Collectors.toList());
        if(objects.size() != 1)
            throw new ORMException("Unsuported id"+objects.size());
        return objects.get(0);
    }

    private DBIdObject prepareIdColumn(Field field) {
        DBIdObject dbIdObject = new DBIdObject();
        DBColumn columnAnnotation = field.getAnnotation(DBColumn.class);
        DBGeneratedValue generetedValueAnnotation = field.getAnnotation(DBGeneratedValue.class);
        dbIdObject.setField(field);
        if(columnAnnotation != null){
            dbIdObject.setColumnName((AnnotationUtils.preparePropertyName(columnAnnotation.name(),field.getName())));
        }
        else dbIdObject.setColumnName(field.getName());
        if(AnnotationUtils.hasAnnotation(field,DBGeneratedValue.class)){
            dbIdObject.setAutoGenereted(true);
            dbIdObject.setGenerationType(generetedValueAnnotation.strategy());
            dbIdObject.setSequenceName(generetedValueAnnotation.sequenceName());
        }else {
            dbIdObject.setAutoGenereted(false);
        }

        dbIdObject.setConverter(register.getConverter(field.getType()));



        return dbIdObject;
    }
}
