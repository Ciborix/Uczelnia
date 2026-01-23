package comparator.Osoba;


import osoba.Osoba;

import java.util.Comparator;

public class PoNazwiskuImieniu implements Comparator<Osoba> {
    @Override
    public int compare(Osoba o1, Osoba o2) {
        int res = o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
        if (res != 0) return res;
        return o1.getImie().compareToIgnoreCase(o2.getImie());
    }
}