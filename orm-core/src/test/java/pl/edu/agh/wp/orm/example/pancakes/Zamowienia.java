package pl.edu.agh.wp.orm.example.pancakes;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;
import pl.ed.agh.wp.orm.annotations.enums.TemporalType;

import java.util.Date;
import java.util.List;

@DBTable
public class Zamowienia {

    @DBId
    private Integer idzamowienia;

    @DBManyToOne(fetch = DBFetchType.EAGER)
    @DBJoinColumn(columnName = "idklienta", tableName = "Klienci")
    private Klienci klient;

    @DBColumn
    @Temporal(type = TemporalType.DATE)
    private Date datarealizacji;

    @DBOneToMany(fetch = DBFetchType.EAGER)
    @DBJoinColumn(columnName = "id")
    private List<Artykuly> artykuly;

    public Zamowienia() {
    }

    public Zamowienia(Integer idzamowienia, Klienci klient, Date datarealizacji) {
        this.idzamowienia = idzamowienia;
        this.klient = klient;
        this.datarealizacji = datarealizacji;
        this.artykuly = artykuly;
    }

    public Integer getIdzamowienia() {
        return idzamowienia;
    }

    public void setIdzamowienia(Integer idzamowienia) {
        this.idzamowienia = idzamowienia;
    }

    public Klienci getKlient() {
        return klient;
    }

    public void setKlient(Klienci klient) {
        this.klient = klient;
    }

    public Date getDatarealizacji() {
        return datarealizacji;
    }

    public void setDatarealizacji(Date datarealizacji) {
        this.datarealizacji = datarealizacji;
    }

    public List<Artykuly> getArtykuly() {
        return artykuly;
    }

    public void setArtykuly(List<Artykuly> artykuly) {
        this.artykuly = artykuly;
    }
}