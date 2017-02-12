package pl.edu.agh.wp.orm.converter;

import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;
import pl.edu.agh.wp.orm.converter.types.*;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.exception.ORMUnsupportedTypeConvert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultTypeRegister implements TypeRegister {

    private Map<Class,TypeConverter> register;

    public DefaultTypeRegister(){
        register = new ConcurrentHashMap<>();
        addRegisterDefault();

    }

    @Override
    public void register(TypeConverter type) {
        register.put(type.getObjectClass(),type);
    }

    @Override
    public void unregister(TypeConverter type) {
        register.remove(type.getObjectClass());
    }

    @Override
    public TypeConverter getConverter(Class clazz, DatabaseTypes dbtype) {
        TypeConverter converter =  register.get(clazz);
        if( converter == null || converter.getType() != dbtype)
            throw new ORMUnsupportedTypeConvert("Cannot find converter for "
                    +clazz.getName()+" " +dbtype.toString());
        return converter;

    }

    @Override
    public TypeConverter getConverter(Class clazz) {
        return register.get(clazz);
    }

    private void addRegisterDefault() {
        register(new StringType());
        register(new IntegerType());
        register(new DoubleType());
        register(new BooleanType());
        register(new FloatType());
        register(new LongType());
    }
}
