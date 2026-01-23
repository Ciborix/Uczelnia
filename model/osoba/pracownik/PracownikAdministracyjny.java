package model.osoba.pracownik;


import model.osoba.PrzedstawSie;

public class PracownikAdministracyjny extends PracownikUczelni implements PrzedstawSie {
    private int liczbNadgodzin;

    public PracownikAdministracyjny(String imie, String nazwisko, String pesel, int wiek, String plec, String stanowisko,
                                    double stazPracy, double pensja, int liczbaNadgodzin){
        super(imie,nazwisko,pesel,wiek,plec,stanowisko,stazPracy,pensja);
        this.liczbNadgodzin = liczbaNadgodzin;
    }

    public int getLiczbNadgodzin() {return liczbNadgodzin;}

    @Override
    public String toString() {
        return super.toString() + "\nLiczba Nadgodzin: " + getLiczbNadgodzin();
    }

    @Override
    public String przedstawSie() {
        return "Pracownik Administracyjny: " + getImie() + " " + getNazwisko() + " Pesel: " + getPesel();
    }
}
