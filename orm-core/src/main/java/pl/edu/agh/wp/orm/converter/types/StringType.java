package pl.edu.agh.wp.orm.converter.types;

import pl.edu.agh.wp.orm.postres.DatabaseTypes;

public class StringType implements TypeConverter {
    @Override
    public Class getObjectClass() {
        return String.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.VARCHAR;
    }

    @Override
    public String getAsString(Object obj) {
        String string = (String) obj;

        return"'"+string.toString()+"'" ;
    }

    @Override
    public Object getObject(String dbfield) {
        return dbfield;
    }
}
