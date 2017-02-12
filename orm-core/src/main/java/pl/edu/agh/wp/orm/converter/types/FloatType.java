package pl.edu.agh.wp.orm.converter.types;


import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

public class FloatType implements TypeConverter{
    @Override
    public Class getObjectClass() {
        return Float.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.NUMERIC;
    }

    @Override
    public String getAsString(Object obj) {
        return obj.toString();
    }

    @Override
    public Object getObject(String dbField) {
        return Float.valueOf(dbField);
    }
}

