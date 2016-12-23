package pl.edu.agh.wp.orm.converter.types.time;

import pl.edu.agh.wp.orm.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.postres.DatabaseTypes;

import java.sql.SQLData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateType implements TypeConverter {
    private static String DATE_FORMAT ="yyyy-mm-dd";
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
        return sqlDate.toString();
    }

    @Override
    public Object getObject(String dbfield) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(dbfield);
        return  new Date(sqlDate.getTime());
    }
}
