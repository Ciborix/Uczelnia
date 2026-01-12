package uczelnia.strategia;

import uczelnia.osoba.pracownik.PracownikUczelni;

public class BonusStazowy implements IBonusStrategia {
    @Override
    public double obliczBonus(PracownikUczelni p) {
        return 0;
    }
}
