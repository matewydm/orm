package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.*;

@DBTable
public class PhoneNumber {
    @DBId
    @DBGeneratedValue(sequenceName = "Gen")
    @DBColumn(name = "phone_id")
    private Integer id;

    @DBColumn(name = "model", maxLength = 50)
    private String model;

    @DBColumn(name = "version", maxLength = 50)
    private String version;

    @DBManyToOne
    private Person person;

    @DBManyToOne
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
