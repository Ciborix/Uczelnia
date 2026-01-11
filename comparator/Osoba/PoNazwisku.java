package uczelnia.comparator.Osoba;

import uczelnia.osoba.Osoba;

import java.util.Comparator;

public class PoNazwisku implements Comparator<Osoba> {
    @Override
    public int compare(Osoba o1, Osoba o2) {
        return o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
    }
}
