package pl.edu.agh.wp.orm.example.pancakes;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

import java.util.List;
import java.util.Set;

@DBTable
public class Klienci {

    @DBId
    private Integer idklienta;

    @DBColumn
    private String nazwa;

    @DBColumn
    private String ulica;

    @DBColumn
    private String miejscowosc;

    @DBColumn
    private String kod;

    @DBColumn
    private String telefon;

    @DBOneToMany(fetch = DBFetchType.EAGER)
    @DBJoinColumn(columnName = "idklienta")
    private Set<Zamowienia> zamowienia;

    public Klienci(Integer idklienta, String nazwa, String ulica, String miejscowosc, String kod, String telefon) {
        this.idklienta = idklienta;
        this.nazwa = nazwa;
        this.ulica = ulica;
        this.miejscowosc = miejscowosc;
        this.kod = kod;
        this.telefon = telefon;

    }

    public Klienci() {
    }



    public Set<Zamowienia> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Set<Zamowienia> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public Integer getIdklienta() {
        return idklienta;
    }

    public void setIdklienta(Integer idklienta) {
        this.idklienta = idklienta;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
