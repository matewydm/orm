package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.*;

public class Address {

    @DBId
    @DBGeneratedValue
    @DBColumn(name = "Address_ID")
    private Integer Id;


    @DBManyToOne
    @DBColumn(name = "person_id")
    private Person person;

    @DBColumn
    private String street;

    public Address(){
        street = "darestreet";
        person = new Person();
//        person.setId(100);

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
