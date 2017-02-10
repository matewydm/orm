package pl.edu.agh.wp.orm.converter.types.time;

import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

import java.sql.Time;
import java.util.Date;

public class DateTimeType implements TypeConverter {
    @Override
    public Class getObjectClass() {
        return Date.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.TIME;
    }

    @Override
    public String getAsString(Object obj) {
        Time sqTime = new Time(((Date)obj).getTime());
        return  "'"+sqTime.toString()+ "'";
    }

    @Override
    public Object getObject(String dbfield) {
        Time sqTime = Time.valueOf(dbfield);
        return new Date(sqTime.getTime());
    }
}
