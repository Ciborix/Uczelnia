package uczelnia.strategia;

import uczelnia.osoba.pracownik.PracownikUczelni;

public class BonusStazowy implements IBonusStategia{
    @Override
    public double obliczBonus(PracownikUczelni p) {
        return 0;
    }
}
