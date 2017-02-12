package pl.edu.agh.wp.orm.example.pancakes;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

import java.util.Date;
import java.util.List;

@DBTable
public class Zamowienia {

    @DBId
    @DBGeneratedValue(sequenceName = "Gen")
    private Integer idzamowienia;

    @DBManyToOne(fetch = DBFetchType.EAGER)
    @DBJoinColumn(columnName = "idklienta", tableName = "Klienci")
    private Klienci klient;

    @DBColumn
    private Date datarealizacji;

    @DBOneToMany(fetch = DBFetchType.LAZY)
    @DBJoinColumn(columnName = "id")
    private List<Artykuly> artykuly;



}