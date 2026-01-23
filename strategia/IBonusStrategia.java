package strategia;


import osoba.pracownik.PracownikUczelni;

import java.io.Serializable;

public interface IBonusStrategia extends Serializable {
    double obliczBonus(PracownikUczelni p);
}
