package pl.edu.agh.wp.orm.example.pancakes;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

@DBTable
public class Artykuly {

    @DBId
    @DBGeneratedValue
    private Integer id_Artykulu;

    @DBManyToOne(fetch = DBFetchType.EAGER)
    @DBJoinColumn(columnName = "idzamowienia", tableName = "Zamowienia")
    private Zamowienia zamowienia;

    @DBColumn
    private Integer sztuk;

    public Artykuly() {
    }

    public Artykuly(Integer id_Artykulu, Integer sztuk) {
        this.id_Artykulu = id_Artykulu;
        this.sztuk = sztuk;
    }

    public Integer getId_Artykulu() {
        return id_Artykulu;
    }

    public void setId_Artykulu(Integer id_Artykulu) {
        this.id_Artykulu = id_Artykulu;
    }

    public Zamowienia getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Zamowienia zamowienia) {
        this.zamowienia = zamowienia;
    }

    public Integer getSztuk() {
        return sztuk;
    }

    public void setSztuk(Integer sztuk) {
        this.sztuk = sztuk;
    }
}
