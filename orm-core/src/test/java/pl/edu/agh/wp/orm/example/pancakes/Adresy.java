package pl.edu.agh.wp.orm.example.pancakes;

import pl.ed.agh.wp.orm.annotations.*;
import pl.ed.agh.wp.orm.annotations.enums.DBFetchType;

import java.util.List;

@DBTable
public class Adresy {

    @DBId
    private String id_adresu;

    @DBManyToOne(fetch = DBFetchType.EAGER)
    private Klienci klienci;

    @DBOneToMany(fetch = DBFetchType.EAGER)
    private List<Zamowienia> zamowienia;

    @DBColumn
    private String ulica;

    @DBColumn
    private Integer numer_domu;

    @DBColumn
    private Integer numer_mieszkania;

    public String getId_adresu() {
        return id_adresu;
    }

    public void setId_adresu(String id_adresu) {
        this.id_adresu = id_adresu;
    }

    public Klienci getKlienci() {
        return klienci;
    }

    public void setKlienci(Klienci klienci) {
        this.klienci = klienci;
    }

    public List<Zamowienia> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienia> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public Integer getNumer_domu() {
        return numer_domu;
    }

    public void setNumer_domu(Integer numer_domu) {
        this.numer_domu = numer_domu;
    }

    public Integer getNumer_mieszkania() {
        return numer_mieszkania;
    }

    public void setNumer_mieszkania(Integer numer_mieszkania) {
        this.numer_mieszkania = numer_mieszkania;
    }
}
