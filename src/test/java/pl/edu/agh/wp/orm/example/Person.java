package pl.edu.agh.wp.orm.example;

import pl.edu.agh.wp.orm.annotations.DBColumn;

public class Person {

    @DBColumn
    private String firstname ="Mati";

    @DBColumn(name = "Name")
    private String lastname ="xd";

}
