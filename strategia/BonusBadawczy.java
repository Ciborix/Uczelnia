package uczelnia.strategia;

import uczelnia.osoba.pracownik.PracownikBadawczoDydaktyczny;
import uczelnia.osoba.pracownik.PracownikUczelni;

public class BonusBadawczy implements IBonusStrategia {
    @Override
    public double obliczBonus(PracownikUczelni p) {
        if (p instanceof PracownikBadawczoDydaktyczny pb) {
            return pb.getLiczbaPublikacji() * 100.0;
        }
        return 0;
    }
}
