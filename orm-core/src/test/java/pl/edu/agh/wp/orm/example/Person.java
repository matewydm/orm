package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.ed.agh.wp.orm.annotations.Temporal;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;

import java.util.Date;

@DBTable
public class Person {


    @DBColumn
    private String firstname ="Mati";

    @DBColumn(name = "Name")
    private String lastname ="xd";

    @DBColumn(name = "CUDO_AGE")
    private Integer age = 20;

    @DBColumn
    @Temporal(type = TemporalType.DATE)
    private Date date = new Date();

}
