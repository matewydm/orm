package pl.edu.agh.wp.orm.converter;

import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

public interface TypeRegister {
    void register(TypeConverter type);
    void unregister(TypeConverter type);
    TypeConverter getConverter(Class clazz, DatabaseTypes dbtype);
    TypeConverter getConverter(Class clazz);
}
