package pl.edu.agh.wp.orm.mapper;

import pl.edu.agh.wp.orm.dto.DBOneToManyReference;

import java.lang.reflect.Field;
import java.util.List;

public interface OneToManyMapper {
    List<DBOneToManyReference> getManyToOneReferences(List<Field> fields);

}
