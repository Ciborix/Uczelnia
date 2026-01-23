package model.strategia;

import model.osoba.pracownik.PracownikAdministracyjny;
import model.osoba.pracownik.PracownikUczelni;

public class BonusAdministracyjny implements IBonusStrategia {
    @Override
    public double obliczBonus(PracownikUczelni p) {
        if (p instanceof PracownikAdministracyjny pa){
            return pa.getLiczbNadgodzin() * 50.0;
        }
        return 0;
    }
}
