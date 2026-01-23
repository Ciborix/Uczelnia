package model.comparator.Kursy;

import model.osoba.student.Kurs;

import java.util.Comparator;

public class PoEctsNazwiskuProw implements Comparator<Kurs> {
    @Override
    public int compare(Kurs k1, Kurs k2) {
        int res = Integer.compare(k1.getPunktyECTS(), k2.getPunktyECTS());
        if (res != 0) return res;
        return k1.getProwadzacy().compareToIgnoreCase(k2.getProwadzacy());
    }
}