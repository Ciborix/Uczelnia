package strategia;

import osoba.pracownik.PracownikAdministracyjny;
import osoba.pracownik.PracownikUczelni;

public class BonusAdministracyjny implements IBonusStrategia {
    @Override
    public double obliczBonus(PracownikUczelni p) {
        if (p instanceof PracownikAdministracyjny pa){
            return pa.getLiczbNadgodzin() * 50.0;
        }
        return 0;
    }
}
