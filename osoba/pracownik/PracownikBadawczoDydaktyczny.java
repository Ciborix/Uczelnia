package osoba.pracownik;

import osoba.PrzedstawSie;

public class PracownikBadawczoDydaktyczny extends PracownikUczelni implements PrzedstawSie {
    private int liczbaPublikacji;

    public PracownikBadawczoDydaktyczny(String imie, String nazwisko, String pesel, int wiek, String plec,
                                        String stanowisko, double stazPracy, double pensja, int liczbaPublikacji){
        super(imie, nazwisko, pesel, wiek, plec, stanowisko, stazPracy, pensja);
        this.liczbaPublikacji = liczbaPublikacji;
    }

    public int getLiczbaPublikacji() {return liczbaPublikacji;}

    @Override
    public String toString() {
        return super.toString() + "\nLiczba Publikacji: " + getLiczbaPublikacji();
    }


    @Override
    public String przedstawSie() {
        return "Pracownik Badawczo-Dydaktyczny: " + getImie() + " " + getNazwisko() + " Pesel: " + getPesel();
    }
}
