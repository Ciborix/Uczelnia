package model.osoba;

import java.io.Serializable;

public abstract class Osoba implements Serializable, PrzedstawSie {
    private static final long serialVersionUID = 1L;

    private String imie;
    private String nazwisko;
    private String pesel;
    private int wiek;
    private String plec;

    public Osoba(String imie, String nazwisko, String pesel, int wiek, String plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.wiek = wiek;
        this.plec = plec;
    }


    public String getImie() {return imie;}
    public String getNazwisko() {return nazwisko;}
    public String getPesel() {return pesel;}
    public int getWiek() {return wiek;}
    public String getPlec() {return plec;}

    @Override
    public String toString()
    {
        return "Imie: " + getImie() + "\nNazwisko: " + getNazwisko() + "\nPesel: "
                + getPesel() + "\nWiek: " + getWiek() + "\nPłeć: " + getPlec();
    }
    public String przedstawSie()
    {
        return getImie() + " " + getNazwisko();
    }

}
