package pl.edu.agh.wp.orm.converter;

import pl.edu.agh.wp.orm.postres.DatabaseTypes;

public class IntegerType implements  Type {
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
