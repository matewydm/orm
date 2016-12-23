package pl.edu.agh.wp.orm.converter;

import pl.ed.agh.wp.orm.annotations.Temporal;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;
import pl.edu.agh.wp.orm.converter.types.time.DateTimeStamp;
import pl.edu.agh.wp.orm.converter.types.time.DateTimeType;
import pl.edu.agh.wp.orm.converter.types.time.DateType;
import pl.edu.agh.wp.orm.exception.ORMUnsupportedTypeConvert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TemporalRegister implements TypeRegister {
    Map<TemporalType,TypeConverter> converters;

    public TemporalRegister(){
        converters = new ConcurrentHashMap<>(3);
        registerDefault();

    }

    private void registerDefault() {
        register(new DateTimeStamp());
        register(new DateTimeType());
        register(new DateType());
    }

    @Override
    public void register(TypeConverter type) {
        TemporalType temporal = getTemporalType(type.getType());
        converters.put(temporal,type);
    }
    private TemporalType getTemporalType(DatabaseTypes type){

        TemporalType temprolaType;
        if(type == DatabaseTypes.DATE)
            temprolaType = TemporalType.DATE;
        else if(type == DatabaseTypes.TIMESTAMP)
            temprolaType = TemporalType.TIMESTAMP;
        else if(type == DatabaseTypes.TIME)
            temprolaType = TemporalType.TIME;
        else throw  new ORMUnsupportedTypeConvert("Cannot parse "+type +" to temporal type");

        return temprolaType;
    }
    @Override
    public void unregister(TypeConverter type) {
        TemporalType temporal = getTemporalType(type.getType());
        converters.remove(temporal);

    }

    @Override
    public TypeConverter getConverter(Class clazz, DatabaseTypes dbtype) {
        TemporalType temporal = getTemporalType(dbtype);
        return converters.get(temporal);
    }

    @Override
    public TypeConverter getConverter(Class clazz) {
        //TODO zroibć logike
        throw new UnsupportedOperationException();
    }

    public TypeConverter getConverter(TemporalType type){
        return converters.get(type);
    }
}
