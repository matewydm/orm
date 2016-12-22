package pl.edu.agh.wp.orm.converter.types;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateTypeTest {
    @Test
    public void getAsString() throws Exception {
        DateType type = new DateType();
        String dateString = "2016-12-30";
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-mm-dd");
        Date date = format.parse(dateString);
        Assert.assertEquals(type.getAsString(date),dateString);
    }

}