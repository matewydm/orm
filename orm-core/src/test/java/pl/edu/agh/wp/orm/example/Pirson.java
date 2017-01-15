package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBId;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.ed.agh.wp.orm.annotations.Temporal;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;

import java.util.Date;

@DBTable
public class Pirson {

    @DBId
    @DBColumn(name = "PirsonID")
    private Integer Id;

    @DBColumn
    private String firstname ;

    @DBColumn(name = "Name")
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