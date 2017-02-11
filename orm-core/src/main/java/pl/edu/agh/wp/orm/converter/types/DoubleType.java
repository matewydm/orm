package pl.edu.agh.wp.orm.converter.types;


import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

public class DoubleType implements TypeConverter{
    @Override
    public Class getObjectClass() {
        return Double.class;
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
        return Double.valueOf(dbField);
    }
}

