package pl.edu.agh.wp.orm.converter.types;

import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.postres.DatabaseTypes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateType implements  Type {
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
        SimpleDateFormat frm = new SimpleDateFormat(DATE_FORMAT);
        return frm.format(obj).toString();
    }

    @Override
    public Object getObject(String dbfield) {
        SimpleDateFormat frm = new SimpleDateFormat(DATE_FORMAT);
        try {
            return frm.parse(dbfield);
        } catch (ParseException e) {
            throw new ORMException(e.getMessage(),e);
        }
    }
}
