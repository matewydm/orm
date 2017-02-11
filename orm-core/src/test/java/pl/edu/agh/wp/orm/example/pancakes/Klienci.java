package pl.edu.agh.wp.orm.example.pancakes;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBId;
import pl.ed.agh.wp.orm.annotations.DBOneToMany;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

import java.util.List;

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
    private List<Zamowienia> zamowienia;

    public List<Zamowienia> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienia> zamowienia) {
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
