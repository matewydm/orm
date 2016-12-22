package pl.edu.agh.wp.orm.converter;

import pl.edu.agh.wp.orm.converter.types.Type;

public interface TypeRegister {
    void register(Type type);
    void unregister(Type type);
}
