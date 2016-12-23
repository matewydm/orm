package pl.edu.agh.wp.orm.converter;

import pl.edu.agh.wp.orm.converter.types.IntegerType;
import pl.edu.agh.wp.orm.converter.types.StringType;
import pl.edu.agh.wp.orm.converter.types.TypeConverter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultTypeRegister implements TypeRegister {

    private Map<Class,TypeConverter> register;

    DefaultTypeRegister(){
        register = new ConcurrentHashMap<>();

    }

    @Override
    public void register(TypeConverter type) {
        register.put(type.getObjectClass(),type);
    }

    @Override
    public void unregister(TypeConverter type) {
        register.remove(type.getObjectClass());
    }

    private void addRegisterDefault() {
        register(new StringType());
        register(new IntegerType());
//        registernew
    }
}
