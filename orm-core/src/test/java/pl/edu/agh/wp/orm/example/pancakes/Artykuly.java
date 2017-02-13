package pl.edu.agh.wp.orm.example.pancakes;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

@DBTable
public class Artykuly {

    @DBId
    @DBGeneratedValue
    private Integer idArtykulu;

    @DBManyToOne(fetch = DBFetchType.EAGER)
    @DBJoinColumn(columnName = "idzamowienia", tableName = "Zamowienia")
    private Zamowienia zamowienia;

    @DBColumn
    private Integer sztuk;

    public Artykuly() {
    }

    public Artykuly(Integer idArtykulu, Integer sztuk) {
        this.idArtykulu = idArtykulu;
        this.sztuk = sztuk;
    }

    public Integer getIdArtykulu() {
        return idArtykulu;
    }

    public void setIdArtykulu(Integer idArtykulu) {
        this.idArtykulu = idArtykulu;
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
