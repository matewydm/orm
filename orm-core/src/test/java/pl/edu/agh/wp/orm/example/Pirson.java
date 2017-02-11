package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;

import java.util.Date;

@DBTable
public class Pirson {

    @DBId
    @DBColumn(name = "PirsonID")
    private Integer Id=12;

    @DBColumn(maxLength = 100)
    private String firstname ;

    @DBColumn(name = "Name", maxLength = 100)
    private String lastname ;

    @DBColumn(name = "CUDO_AGE")
    private Integer age;

    @DBColumn()
    @Temporal(type = TemporalType.DATE)
    private Date date ;


    public Pirson(){
        firstname ="Mati";
        lastname ="xd";
        age = 20;
        date = new Date();
    }

    public Pirson(String fName,String lName){
        firstname = fName;
        lastname =lName;
        age =20;
        date = new Date();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }

    public Date getDate() {
        return date;
    }



    public String toString() {
        return "Person: " + firstname + " " + lastname + " " + age + " " + date.toString();
    }
}