package uczelnia.comparator.Osoba;

import uczelnia.osoba.Osoba;
import java.util.Comparator;

public class PoNazwiskuWieku implements Comparator<Osoba> {
    @Override
    public int compare(Osoba o1, Osoba o2) {
        int res = o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
        if (res != 0) return res;
        // Wiek malejąco: o2 porówujemy do o1
        return Integer.compare(o2.getWiek(), o1.getWiek());
    }
}