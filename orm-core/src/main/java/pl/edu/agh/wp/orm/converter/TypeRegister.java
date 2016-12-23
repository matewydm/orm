package pl.edu.agh.wp.orm.converter;

import pl.edu.agh.wp.orm.converter.types.TypeConverter;

public interface TypeRegister {
    void register(TypeConverter type);
    void unregister(TypeConverter type);
}
