package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DBTable
public class Person {

    @DBId
    @DBGeneratedValue(sequenceName = "Gen")
    @DBColumn(name = "per_id")
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void setTransients(Integer transients) {
        this.transients = transients;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DBColumn(name = "name", maxLength = 50)
    private String firstname ;

    @DBColumn(name = "lastname", maxLength = 50)
    private String lastname ;

    @DBColumn(name = "age")
    private Integer age;

    @DBOneToMany
    private List<Address> address;
    private Integer transients;

    @DBColumn(name = "birth_date")
    @Temporal(type = TemporalType.DATE)
    private Date date ;
    public Person(){

        address= new ArrayList<>();
        firstname ="Mati";
        lastname ="xd";
        age = 20;
        date = new Date();
    }

    public Person(String fName,String lName){
        address= new ArrayList<>();

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

    public Integer getId() {
        return id;
    }

    public String toString() {
        return "Person: " + firstname + " " + lastname + " " + age + " " + date.toString();
    }
}
