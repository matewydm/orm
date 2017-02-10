package pl.edu.agh.wp.orm.converter.types;

import org.junit.Assert;
import org.junit.Test;
import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;
import pl.edu.agh.wp.orm.converter.types.time.DateTimeType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTypeTest {
    @Test
    public void getAsString() throws Exception {
        TypeConverter type = new DateTimeType();
        String timeString = "12:30:10";
        SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss");
        Date date = format.parse(timeString);
        Assert.assertEquals("'"+timeString+"'",type.getAsString(date));
    }

    @Test
    public void getObject() throws Exception {
        TypeConverter type = new DateTimeType();
        String timeString = "12:30:10";
        SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss");
        Date date = format.parse(timeString);

        Assert.assertEquals(date,type.getObject(timeString));
    }

}