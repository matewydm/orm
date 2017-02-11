package pl.edu.agh.wp.orm.mapper.annotation;

import pl.ed.agh.wp.orm.annotations.DBOneToMany;
import pl.edu.agh.wp.orm.dto.DBOneToManyReference;
import pl.edu.agh.wp.orm.mapper.OneToManyMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationOneToManyMapper implements OneToManyMapper {

    @Override
    public List<DBOneToManyReference> getOneToManyReferences (List<Field> fields) {
        List<DBOneToManyReference> objects =fields.stream()

                .map(this::prepareReference).
                        collect(Collectors.toList());
        return objects;
    }

    private DBOneToManyReference prepareReference(Field field) {
        DBOneToManyReference reference = new DBOneToManyReference();
            DBOneToMany annotation = field.getAnnotation(DBOneToMany.class);
        reference.setFetch(annotation.fetch());
        ParameterizedType fieldType = (ParameterizedType) field.getGenericType();
        Class genericType = (Class<?>) fieldType.getActualTypeArguments()[0];
        reference.setJointedClass(genericType);
        //TODO nazwa tabeli i kolumny
        return reference;
    }
}
