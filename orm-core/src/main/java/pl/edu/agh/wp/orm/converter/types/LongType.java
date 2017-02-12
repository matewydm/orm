package pl.edu.agh.wp.orm.converter.types;


import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

public class LongType implements TypeConverter{
    @Override
    public Class getObjectClass() {
        return Long.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.BIGINT;
    }

    @Override
    public String getAsString(Object obj) {
        return obj.toString();
    }

    @Override
    public Object getObject(String dbField) {
        return Long.valueOf(dbField);
    }
}

