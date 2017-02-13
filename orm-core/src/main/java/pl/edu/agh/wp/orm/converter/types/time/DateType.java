package pl.edu.agh.wp.orm.converter.types.time;

import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

import java.util.Date;

public class DateType implements TypeConverter {
    @Override
    public Class getObjectClass() {
        return Date.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.DATE;
    }

    @Override
    public String getAsString(Object obj) {

        java.sql.Date sqlDate = new java.sql.Date(((Date) obj).getTime());
        return  "'"+sqlDate.toString()+ "'";
    }

    @Override
    public Object getObject(String dbfield) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(dbfield);
        return  new Date(sqlDate.getTime());
    }
}
