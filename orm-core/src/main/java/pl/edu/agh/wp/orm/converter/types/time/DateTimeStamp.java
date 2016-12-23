package pl.edu.agh.wp.orm.converter.types.time;

import pl.edu.agh.wp.orm.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.postres.DatabaseTypes;

import java.sql.Timestamp;
import java.util.Date;

public class DateTimeStamp implements TypeConverter {
    @Override
    public Class getObjectClass() {
        return Date.class;
    }

    @Override
    public DatabaseTypes getType() {
        return DatabaseTypes.TIMESTAMP;
    }

    @Override
    public String getAsString(Object obj) {
        Timestamp sqlTimestamp = new Timestamp(((Date) obj).getTime());
        return sqlTimestamp.toString();
    }

    @Override
    public Object getObject(String dbfield) {
        Timestamp sqlTimestamp = Timestamp.valueOf(dbfield);
        return new Date(sqlTimestamp.getTime());
    }
}
