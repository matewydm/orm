package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBTable;

@DBTable
public class Person {

    @DBColumn
    private String firstname ="Mati";

    @DBColumn(name = "Name")
    private String lastname ="xd";

}
