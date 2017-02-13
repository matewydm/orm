package pl.edu.agh.wp.orm.converter.types;


import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

public class BooleanType implements TypeConverter{
    @Override
    public Class getObjectClass() {
        return Boolean.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.BOOLEAN;
    }

    @Override
    public String getAsString(Object obj) {
        return obj.toString();
    }

    @Override
    public Object getObject(String dbField) {
        return Boolean.valueOf(dbField);
    }
}

