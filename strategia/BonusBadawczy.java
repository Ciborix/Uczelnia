package uczelnia.strategia;

import uczelnia.osoba.pracownik.PracownikUczelni;

public class BonusBadawczy implements IBonusStategia{
    @Override
    public double obliczBonus(PracownikUczelni p) {
        return 0;
    }
}
