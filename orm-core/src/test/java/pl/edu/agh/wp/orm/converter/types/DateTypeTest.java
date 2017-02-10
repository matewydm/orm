package pl.edu.agh.wp.orm.converter.types;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.wp.orm.converter.types.time.DateType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeTest {
    @Test
    public void getAsString() throws Exception {
        DateType type = new DateType();
        String dateString = "2016-12-30";
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-d");
        Date date = format.parse(dateString);
        Assert.assertEquals(type.getAsString(date),"'"+dateString+"'");
    }

    @Test
    public  void getObject() throws Exception{
        DateType type = new DateType();
        String dateString = "2016-12-30";
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-d");
        Date date = format.parse(dateString);

        Assert.assertEquals(date,type.getObject(dateString));
    }

}