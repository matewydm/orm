package pl.edu.agh.wp.orm.converter.types;

import pl.edu.agh.wp.orm.postres.DatabaseTypes;

public class IntegerType implements TypeConverter {
    @Override
    public Class getObjectClass() {
        return Integer.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.INTEGER;
    }

    @Override
    public String getAsString(Object obj) {
        return obj.toString();
    }

    @Override
    public Object getObject(String dbfield) {
        return Integer.valueOf(dbfield);
    }
}
