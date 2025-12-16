package uczelnia.osoba.pracownik;

import uczelnia.osoba.Osoba;

public abstract class PracownikUczelni extends Osoba {
    private String stanowisko;
    private double stazPracy;
    private double pensja;

    public PracownikUczelni(String imie, String nazwisko, String pesel, int wiek, String plec,
                            String stanowisko, double stazPracy, double pensja)
    {
        super(imie, nazwisko, pesel, wiek, plec);
        this.stanowisko = stanowisko;
        this.stazPracy = stazPracy;
        this.pensja = pensja;
    }

    public String getStanowisko() {return stanowisko;}
    public double getStazPracy() {return stazPracy;}
    public double getPensja() {return pensja;}


    @Override
    public String toString()
    {
        return super.toString() + "\nStanowisko: " + getStanowisko() +"\nStaż pracy: " + getStazPracy() + "\nPensja: " + getPensja();
    }

}
