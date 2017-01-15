package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;

import javax.annotation.Generated;
import java.util.Date;

@DBTable
public class Person {

    @DBId
    @DBGeneratedValue(sequenceName = "Gen")
    private Integer id;

    @DBColumn(name = "name")
    private String firstname ;

    @DBColumn(name = "lastname")
    private String lastname ;

    @DBColumn(name = "age")
    private Integer age;

    @DBOneToMany
    private Address address;
    private Integer transients;

    @DBColumn(name = "birth_date")
    @Temporal(type = TemporalType.DATE)
    private Date date ;
    public Person(){

        address= new Address();
        firstname ="Mati";
        lastname ="xd";
        age = 20;
        date = new Date();
    }

    public Person(String fName,String lName){
        address= new Address();

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
