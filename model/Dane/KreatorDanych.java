package model.Dane;
import model.IOterminal.IOTerminal;
import model.osoba.pracownik.PracownikAdministracyjny;
import model.osoba.pracownik.PracownikBadawczoDydaktyczny;
import model.osoba.student.Kurs;
import model.osoba.student.Student;
import model.strategia.BonusAdministracyjny;
import model.strategia.BonusBadawczy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class KreatorDanych {
    private final IOTerminal ioTerminal;

    public KreatorDanych(IOTerminal ioTerminal) {
        this.ioTerminal = ioTerminal;
    }


    private String wczytajPlec() {
        String plec;
        do {
            plec = ioTerminal.wczytajString("Podaj płeć (M/K)").toUpperCase();
            if (!plec.equals("M") && !plec.equals("K")) {
                System.out.println("Błąd: Płeć musi być 'M' lub 'K'.");
            }
        } while (!plec.equals("M") && !plec.equals("K"));
        return plec;
    }

    private boolean wczytajOpcjeTakNie(String prompt) {
        String odpowiedz;
        do {
            odpowiedz = ioTerminal.wczytajString(prompt + " (T/N)").toUpperCase();
            if (!odpowiedz.equals("T") && !odpowiedz.equals("N")) {
                System.out.println("Błąd: Wprowadź T (Tak) lub N (Nie).");
            }
        } while (!odpowiedz.equals("T") && !odpowiedz.equals("N"));
        return odpowiedz.equals("T");
    }


    public Student utworzStudenta(Scanner scanner) {
        System.out.println("\n--- Tworzenie Studenta ---");

        String imie = ioTerminal.wczytajString("Podaj imię");
        String nazwisko = ioTerminal.wczytajString("Podaj nazwisko");
        String pesel = ioTerminal.wczytajString("Podaj PESEL");
        int wiek = ioTerminal.wczytajInt("Podaj wiek");
        String plec = wczytajPlec();

        int nrIndeks = ioTerminal.wczytajInt("Podaj numer indeksu");
        int rokStudiow = ioTerminal.wczytajInt("Podaj rok studiów");

        List<Kurs> kursyList = new ArrayList<>();

        boolean czyERASMUS = wczytajOpcjeTakNie("Czy student jest na Erasmusie?");

        int stopien = ioTerminal.wczytajInt("Wybierz stopień studiów (1: I stopień, 2: II stopień)");
        boolean czyIStopien = (stopien == 1);
        boolean czyIIStopien = (stopien == 2);

        int tryb = ioTerminal.wczytajInt("Wybierz tryb studiów (1: Stacjonarne, 2: Niestacjonarne)");
        boolean czyStacjonarny = (tryb == 1);
        boolean czyNieStacjonarny = (tryb == 2);


        return new Student(imie, nazwisko, pesel, wiek, plec, nrIndeks, rokStudiow, kursyList,
                czyERASMUS, czyIStopien, czyIIStopien, czyStacjonarny, czyNieStacjonarny);
    }

    public PracownikBadawczoDydaktyczny utworzPracownikaBadawczoDydaktycznego(Scanner scanner) {
        System.out.println("\n--- Tworzenie Pracownika Badawczo-Dydaktycznego ---");

        String imie = ioTerminal.wczytajString("Podaj imię");
        String nazwisko = ioTerminal.wczytajString("Podaj nazwisko");
        String pesel = ioTerminal.wczytajString("Podaj PESEL");
        int wiek = ioTerminal.wczytajInt("Podaj wiek");
        String plec = wczytajPlec();

        String stanowisko = ioTerminal.wczytajString("Podaj stanowisko");
        double stazPracy = ioTerminal.wczytajDouble("Podaj staż pracy (lata)");
        double pensja = ioTerminal.wczytajDouble("Podaj pensję");

        int liczbaPublikacji = ioTerminal.wczytajInt("Podaj liczbę publikacji");

        PracownikBadawczoDydaktyczny pbd = new PracownikBadawczoDydaktyczny(
                imie, nazwisko, pesel, wiek, plec,
                stanowisko, stazPracy, pensja,
                liczbaPublikacji
        );
        pbd.setBonusStategia(new BonusBadawczy());
        return pbd;
    }

    public PracownikAdministracyjny utworzPracownikaAdministracyjnego(Scanner scanner) {
        System.out.println("\n--- Tworzenie Pracownika Administracyjnego ---");


        String imie = ioTerminal.wczytajString("Podaj imię");
        String nazwisko = ioTerminal.wczytajString("Podaj nazwisko");
        String pesel = ioTerminal.wczytajString("Podaj PESEL");
        int wiek = ioTerminal.wczytajInt("Podaj wiek");
        String plec = wczytajPlec();


        String stanowisko = ioTerminal.wczytajString("Podaj stanowisko (np. Specjalista, Kierownik)");
        double stazPracy = ioTerminal.wczytajDouble("Podaj staż pracy (lata)");
        double pensja = ioTerminal.wczytajDouble("Podaj pensję");


        int liczbaNadgodzin = ioTerminal.wczytajInt("Podaj liczbę nadgodzin w miesiącu");

        PracownikAdministracyjny pa = new PracownikAdministracyjny(
                imie, nazwisko, pesel, wiek, plec,
                stanowisko, stazPracy, pensja,
                liczbaNadgodzin
        );
        pa.setBonusStategia(new BonusAdministracyjny());
        return pa;
    }
    public Kurs utworzKurs() {
        System.out.println("\n--- Tworzenie Kursu ---");
        String nazwa = ioTerminal.wczytajString("Podaj nazwę kursu");
        int punktyECTS = ioTerminal.wczytajInt("Podaj punkty ECTS");
        String prowadzacy = ioTerminal.wczytajString("Podaj prowadzacego kursu");
        return new Kurs(nazwa, prowadzacy, punktyECTS);
    }

}