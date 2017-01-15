package pl.edu.agh.wp.orm.mapper;

import pl.edu.agh.wp.orm.dto.DBIdObject;

import java.lang.reflect.Field;
import java.util.List;

public interface IdMapper {

    DBIdObject getIdObject(List<Field> fields);
}
