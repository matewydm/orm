package pl.edu.agh.wp.orm.converter;

import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;

public interface TypeRegister {
    void register(TypeConverter type);
    void unregister(TypeConverter type);
}
