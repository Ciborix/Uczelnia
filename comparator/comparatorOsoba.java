package uczelnia.comparator;

import java.util.Comparator;
import uczelnia.osoba.Osoba;
import uczelnia.osoba.student.Kurs;

/**
 * Klasa zbiorcza przechowująca komparatory wymagane w Etapie 3.
 */
public class comparatorOsoba {

    //Sortowanie po Nazwisku
    public static class PoNazwisku implements Comparator<Osoba> {
        @Override
        public int compare(Osoba o1, Osoba o2) {
            return o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
        }
    }

    //Sortowanie po Nazwisku i Imieniu
    public static class PoNazwiskuImieniu implements Comparator<Osoba> {
        @Override
        public int compare(Osoba o1, Osoba o2) {
            int res = o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
            if (res != 0) return res;
            return o1.getImie().compareToIgnoreCase(o2.getImie());
        }
    }

    //Sortowanie po Nazwisku i Wieku (malejąco)
    public static class PoNazwiskuWieku implements Comparator<Osoba> {
        @Override
        public int compare(Osoba o1, Osoba o2) {
            int res = o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
            if (res != 0) return res;
            // Malejąco: o2 porówujemy do o1
            return Integer.compare(o2.getWiek(), o1.getWiek());
        }
    }

    //Sortowanie Kursów wg ECTS i Nazwiska Prowadzącego
    public static class KursyEctsProwadzacy implements Comparator<Kurs> {
        @Override
        public int compare(Kurs k1, Kurs k2) {
            int res = Integer.compare(k1.getPunktyECTS(), k2.getPunktyECTS());
            if (res != 0) return res;
            return k1.getProwadzacy().compareToIgnoreCase(k2.getProwadzacy());
        }
    }
}