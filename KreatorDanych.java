package uczelnia;

import uczelnia.osoba.pracownik.PracownikAdministracyjny;
import uczelnia.osoba.pracownik.PracownikBadawczoDydaktyczny;
import uczelnia.osoba.student.Student;
import uczelnia.osoba.student.Kurs;
import uczelnia.osoba.Osoba;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KreatorDanych {
    private final IOTerminal ioTerminal;

    public KreatorDanych(IOTerminal ioTerminal) {
        this.ioTerminal = ioTerminal;
    }



    private String wczytajPlec(Scanner scanner) {
        String plec;
        do {
            plec = ioTerminal.wczytajString(scanner, "Podaj płeć (M/K)").toUpperCase();
            if (!plec.equals("M") && !plec.equals("K")) {
                System.out.println("Błąd: Płeć musi być 'M' lub 'K'.");
            }
        } while (!plec.equals("M") && !plec.equals("K"));
        return plec;
    }

    private boolean wczytajOpcjeTakNie(Scanner scanner, String prompt) {
        String odpowiedz;
        do {
            odpowiedz = ioTerminal.wczytajString(scanner, prompt + " (T/N)").toUpperCase();
            if (!odpowiedz.equals("T") && !odpowiedz.equals("N")) {
                System.out.println("Błąd: Wprowadź T (Tak) lub N (Nie).");
            }
        } while (!odpowiedz.equals("T") && !odpowiedz.equals("N"));
        return odpowiedz.equals("T");
    }


    public Student utworzStudenta(Scanner scanner) {
        System.out.println("\n--- Tworzenie Studenta ---");

        // Pola Osoby
        String imie = ioTerminal.wczytajString(scanner, "Podaj imię");
        String nazwisko = ioTerminal.wczytajString(scanner, "Podaj nazwisko");
        String pesel = ioTerminal.wczytajString(scanner, "Podaj PESEL");
        int wiek = ioTerminal.wczytajInt(scanner, "Podaj wiek");
        String plec = wczytajPlec(scanner);

        // Pola Studenta
        int nrIndeks = ioTerminal.wczytajInt(scanner, "Podaj numer indeksu");
        int rokStudiow = ioTerminal.wczytajInt(scanner, "Podaj rok studiów");

        // Listy (na razie pusta)
        List<Kurs> kursyList = new ArrayList<>();

        // Pola boolean (z walidacją T/N)
        boolean czyERASMUS = wczytajOpcjeTakNie(scanner, "Czy student jest na Erasmusie?");

        int stopien = ioTerminal.wczytajInt(scanner, "Wybierz stopień studiów (1: I stopień, 2: II stopień)");
        boolean czyIStopien = (stopien == 1);
        boolean czyIIStopien = (stopien == 2);

        int tryb = ioTerminal.wczytajInt(scanner, "Wybierz tryb studiów (1: Stacjonarne, 2: Niestacjonarne)");
        boolean czyStacjonarny = (tryb == 1);
        boolean czyNieStacjonarny = (tryb == 2);


        return new Student(imie, nazwisko, pesel, wiek, plec, nrIndeks, rokStudiow, kursyList,
                czyERASMUS, czyIStopien, czyIIStopien, czyStacjonarny, czyNieStacjonarny);
    }

    public PracownikBadawczoDydaktyczny utworzPracownikaBadawczoDydaktycznego(Scanner scanner) {
        System.out.println("\n--- Tworzenie Pracownika Badawczo-Dydaktycznego ---");

        // Pola Osoby
        String imie = ioTerminal.wczytajString(scanner, "Podaj imię");
        String nazwisko = ioTerminal.wczytajString(scanner, "Podaj nazwisko");
        String pesel = ioTerminal.wczytajString(scanner, "Podaj PESEL");
        int wiek = ioTerminal.wczytajInt(scanner, "Podaj wiek");
        String plec = wczytajPlec(scanner);

        // Pola Pracownika Uczelni
        String stanowisko = ioTerminal.wczytajString(scanner, "Podaj stanowisko");
        double stazPracy = ioTerminal.wczytajDouble(scanner, "Podaj staż pracy (lata)");
        double pensja = ioTerminal.wczytajDouble(scanner, "Podaj pensję");

        // Pola Pracownika Badawczo-Dydaktycznego
        int liczbaPublikacji = ioTerminal.wczytajInt(scanner, "Podaj liczbę publikacji");

        return new PracownikBadawczoDydaktyczny(
                imie, nazwisko, pesel, wiek, plec,
                stanowisko, stazPracy, pensja,
                liczbaPublikacji
        );
    }

    public PracownikAdministracyjny utworzPracownikaAdministracyjnego(Scanner scanner) {
        System.out.println("\n--- Tworzenie Pracownika Administracyjnego ---");


        String imie = ioTerminal.wczytajString(scanner, "Podaj imię");
        String nazwisko = ioTerminal.wczytajString(scanner, "Podaj nazwisko");
        String pesel = ioTerminal.wczytajString(scanner, "Podaj PESEL");
        int wiek = ioTerminal.wczytajInt(scanner, "Podaj wiek");
        String plec = wczytajPlec(scanner);


        String stanowisko = ioTerminal.wczytajString(scanner, "Podaj stanowisko (np. Specjalista, Kierownik)");
        double stazPracy = ioTerminal.wczytajDouble(scanner, "Podaj staż pracy (lata)");
        double pensja = ioTerminal.wczytajDouble(scanner, "Podaj pensję");


        int liczbaNadgodzin = ioTerminal.wczytajInt(scanner, "Podaj liczbę nadgodzin w miesiącu");

        return new PracownikAdministracyjny(
                imie, nazwisko, pesel, wiek, plec,
                stanowisko, stazPracy, pensja,
                liczbaNadgodzin
        );
    }
    public Kurs utworzKurs(Scanner scanner, List<PracownikBadawczoDydaktyczny> listaProwadzacych) {
        System.out.println("\n--- Tworzenie Kursu ---");
        String nazwa = ioTerminal.wczytajString(scanner, "Podaj nazwę kursu");
        int punktyECTS = ioTerminal.wczytajInt(scanner, "Podaj punkty ECTS");

        System.out.println("\n--- Wybierz Prowadzącego (tylko Badawczo-Dydaktyczni) ---");
        if (listaProwadzacych.isEmpty()) {
            System.out.println("Brak dostępnych prowadzących. Dodaj pracownika dydaktycznego, aby móc dodać kurs.");
            return null;
        }

        for (int i = 0; i < listaProwadzacych.size(); i++) {
            System.out.println((i + 1) + ". " + listaProwadzacych.get(i).getImie() + " " + listaProwadzacych.get(i).getNazwisko());
        }

        int indeks = ioTerminal.wczytajInt(scanner, "Wybierz numer prowadzącego (1-" + listaProwadzacych.size() + ")");

        if (indeks < 1 || indeks > listaProwadzacych.size()) {
            System.out.println("Nieprawidłowy wybór. Anulowanie.");
            return null;
        }

        PracownikBadawczoDydaktyczny prowadzacy = listaProwadzacych.get(indeks - 1);

        return new Kurs(nazwa, prowadzacy, punktyECTS);
    }

    public static List<PracownikBadawczoDydaktyczny> filtrujProwadzacych(List<Osoba> osoby) {
        List<PracownikBadawczoDydaktyczny> prowadzacy = new ArrayList<>();
        for (Osoba o : osoby) {
            if (o instanceof PracownikBadawczoDydaktyczny) {
                prowadzacy.add((PracownikBadawczoDydaktyczny) o);
            }
        }
        return prowadzacy;
    }
}