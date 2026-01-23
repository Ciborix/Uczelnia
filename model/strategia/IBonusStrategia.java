package model.strategia;


import model.osoba.pracownik.PracownikUczelni;

import java.io.Serializable;

public interface IBonusStrategia extends Serializable {
    double obliczBonus(PracownikUczelni p);
}
