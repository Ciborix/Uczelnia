package uczelnia.strategia;

import uczelnia.osoba.pracownik.PracownikAdministracyjny;
import uczelnia.osoba.pracownik.PracownikUczelni;

public class BonusAdministracyjny implements IBonusStrategia {
    @Override
    public double obliczBonus(PracownikUczelni p) {
        if (p instanceof PracownikAdministracyjny pa){
            return pa.getLiczbNadgodzin() * 50.0;
        }
        return 0;
    }
}
