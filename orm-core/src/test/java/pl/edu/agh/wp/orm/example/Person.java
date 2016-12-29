package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.ed.agh.wp.orm.annotations.Temporal;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;

import java.util.Date;

@DBTable
public class Person {

    public Person(){
        firstname ="Mati";
        lastname ="xd";
        age = 20;
        date = new Date();
    }

    public Person(String fName,String lName){
        firstname = fName;
        lastname =lName;
        age =20;
        date = new Date();
    }
    @DBColumn
    private String firstname ;

    @DBColumn(name = "Name")
    private String lastname ;

    @DBColumn(name = "CUDO_AGE")
    private Integer age;

    @DBColumn
    @Temporal(type = TemporalType.DATE)
    private Date date ;

}
