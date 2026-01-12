package uczelnia.osoba.pracownik;

import uczelnia.osoba.Osoba;
import uczelnia.strategia.IBonusStrategia;

public abstract class PracownikUczelni extends Osoba {
    private String stanowisko;
    private double stazPracy;
    private double pensja;
    private IBonusStrategia bonusStategia;

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

    public double getDodatekStazowy()
    {
        return this.getStazPracy() * 50;
    }

    public void setBonusStategia(IBonusStrategia strategia) {
        this.bonusStategia = strategia;
    }

    public double getSumaWyplaty()
    {
        double baza = getPensja() + getDodatekStazowy();
        if (this.bonusStategia == null) return baza;
        return baza + this.bonusStategia.obliczBonus(this);
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nStanowisko: " + getStanowisko() +"\nStaż pracy: " + getStazPracy() + "\nPensja: " + getPensja();
    }

}
