package pl.edu.agh.wp.orm.mapper;

import pl.edu.agh.wp.orm.dto.DBManyToOneReference;

import java.lang.reflect.Field;
import java.util.List;

public interface ManyToOneMapper {
    List<DBManyToOneReference> getManyToOneReferences(List<Field> fields);


}
