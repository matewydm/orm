package pl.edu.agh.wp.orm.converter;

import pl.edu.agh.wp.orm.converter.types.IntegerType;
import pl.edu.agh.wp.orm.converter.types.StringType;
import pl.edu.agh.wp.orm.converter.types.Type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultTypeRegister implements TypeRegister {

    private Map<Class,Type> register;

    DefaultTypeRegister(){
        register = new ConcurrentHashMap<>();

    }

    @Override
    public void register(Type type) {
        register.put(type.getObjectClass(),type);
    }

    @Override
    public void unregister(Type type) {
        register.remove(type.getObjectClass());
    }

    private void addRegisterDefault() {
        register(new StringType());
        register(new IntegerType());
//        registernew
    }
}
