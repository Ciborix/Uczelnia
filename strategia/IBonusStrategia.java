package uczelnia.strategia;

import uczelnia.osoba.pracownik.PracownikUczelni;

import java.io.Serializable;

public interface IBonusStrategia extends Serializable {
    double obliczBonus(PracownikUczelni p);
}
