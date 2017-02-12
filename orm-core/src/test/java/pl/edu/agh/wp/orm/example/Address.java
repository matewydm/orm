package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.*;

@DBTable
public class Address {

    @DBId
    @DBColumn(name = "Address_ID")
    private Integer Id;


    @DBManyToOne
    private Person person;

    @DBColumn
    private String street;

    public Address(){
        street = "darestreet";
        person = new Person();

    }


}
