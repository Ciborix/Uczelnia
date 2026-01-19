package uczelnia.Kontroler;

import uczelnia.Dane.KontenerDanych;
import uczelnia.IOTerminal;
import uczelnia.MenagerDanych;
import uczelnia.KreatorDanych;
import uczelnia.comparator.Kursy.PoEctsNazwiskuProw;
import uczelnia.comparator.Osoba.PoNazwisku;
import uczelnia.comparator.Osoba.PoNazwiskuImieniu;
import uczelnia.comparator.Osoba.PoNazwiskuWieku;
import uczelnia.osoba.pracownik.PracownikBadawczoDydaktyczny;
import uczelnia.osoba.pracownik.PracownikUczelni;
import uczelnia.osoba.student.Kurs;
import uczelnia.osoba.student.Student;

import java.util.List;
import java.util.Scanner;

public class Kontroler {
    private final KontenerDanych kontener;
    private final IOTerminal terminal;
    private final MenagerDanych menager;
    private final KreatorDanych kreator;
    private final Scanner scanner = new Scanner(System.in);
    private boolean czyProgramDziala = true;

    public Kontroler(KontenerDanych kontener, MenagerDanych menager, IOTerminal terminal) {
        this.kontener = kontener;
        this.menager = menager;
        this.terminal = terminal;
        this.kreator = new KreatorDanych(terminal);

    }

    public void start() {
        while (czyProgramDziala) {
            procesujWyborMenuGlownego(terminal.wyswietlMenuGlowne());
        }
    }

    private void procesujWyborMenuGlownego(int wybor) {
        switch (wybor) {
            case 1 -> zarzadzajWyswietleniem();
            case 2 -> zarzadzajDodawaniem();
            case 3 -> zarzadzajUsuwaniem();
            case 4 -> zarzadzajSortowaniem();
            case 5 -> kontener.drukujListePlac();

            case 6 -> zarzadzajWyszukiwaniem();
            case 7 -> zarzadzajUsuwaniemDuplikatow();

            case 10 -> zarzadzajZapisaniem();
            case 0 -> czyProgramDziala = false;
        }
    }
    private void zarzadzajWyswietleniem() {
        int wybor = terminal.wybierzOpcjeWyswietlania();
        if (wybor == 0) return;

        switch (wybor) {
            case 1 -> {
                kontener.pokazOsoby();
                kontener.pokazWszystkieKursy();
            }
            case 2 -> kontener.pokazWszystkichStudentow();
            case 3 -> kontener.pokazWszystkichPracownikow();
            case 4 -> kontener.pokazWszystkieKursy();
            default -> System.out.println("Nieprawidłowy wybór.");
        }
    }

    private void zarzadzajDodawaniem() {
        int co = terminal.ask("1. Student | 2. Pracownik Badawczy | 3. Pracownik Administracyjny | 4. Kurs");
        if (co == 0) return;
        switch (co) {
            case 1 -> kontener.dodajOsobe(kreator.utworzStudenta(scanner));
            case 2 -> kontener.dodajOsobe(kreator.utworzPracownikaBadawczoDydaktycznego(scanner));
            case 3 -> kontener.dodajOsobe(kreator.utworzPracownikaAdministracyjnego(scanner));
            case 4 -> kontener.dodajKurs(kreator.utworzKurs());
        }
    }

    private void zarzadzajUsuwaniem() {
        int kat = terminal.wybierzKategorieUsuwania();
        if (kat == 0) return;

        int opcja = switch (kat) {
            case 1 -> terminal.wybierzKryteriumUsuwaniaStudenta();
            case 2 -> terminal.wybierzKryteriumUsuwaniaPracownika();
            case 3 -> terminal.wybierzKryteriumUsuwaniaKursu();
            default -> 0;
        };

        if (opcja != 0) {
            String val = terminal.wczytajString("Podaj szukaną wartość");
            if (kat == 1) kontener.usunStudenta(opcja, val);
            else if (kat == 2) kontener.usunPracownika(opcja, val);
            else if (kat == 3) kontener.usunKurs(opcja, val);
        }
    }

    private void zarzadzajSortowaniem() {
        int wybor = terminal.wybierzOpcjeSortowania();
        if (wybor == 0) return;

        switch (wybor) {
            case 1 -> {
                kontener.getOsoby().sort(new PoNazwisku());
                System.out.println("Posortowano osoby po nazwisku.");
            }
            case 2 -> {
                kontener.getOsoby().sort(new PoNazwiskuImieniu());
                System.out.println("Posortowano osoby po nazwisku i imieniu.");
            }
            case 3 -> {
                kontener.getOsoby().sort(new PoNazwiskuWieku());
                System.out.println("Posortowano osoby po nazwisku i wieku (malejąco).");
            }
            case 4 -> {
                kontener.getKursy().sort(new PoEctsNazwiskuProw());
                System.out.println("Posortowano kursy po ECTS i prowadzącym.");
            }
            default -> System.out.println("Nieprawidłowy wybór.");
        }
        kontener.pokazOsoby();
    }
    public void zarzadzajWyszukiwaniem()
    {
        int wybor = terminal.wybierzOpcjeWyszukiwania();
        if (wybor == 0) return;

        switch (wybor) {
            case 1 -> zarzadzajWyszukiwaniemStudentow();
            case 2 -> zarzadzajWyszukiwaniemPracownikow();
            case 3 -> zarzadzajWyszukiwaniemKursow();
        }
    }
    public void zarzadzajWyszukiwaniemPracownikow()
    {
        System.out.println("Podaj po czym chcesz wyszukac (jezeli czegos nie chcesz wpisz NULL)");
        String nazwisko = terminal.wczytajString("Podaj nazwisko (albo wpisz null)");
        String imie = terminal.wczytajString("Podaj imie (albo wpisz null)");
        String stanowisko= terminal.wczytajString("Podaj stanowisko (albo wpisz null)");
        Double stazpracy= terminal.wczytajDouble("Podaj imie (albo wpisz null)");
        Integer liczbaNadgodzin = terminal.wczytajInt("Podaj liczbe nadgodzin (albo wpisz null)");
        Double pensja = terminal.wczytajDouble("Podaj ilosc pensji (albo wpisz null)");
        System.out.println(" ");
        List<PracownikUczelni> p = kontener.wyszukajPracownika(nazwisko,imie,stanowisko,stazpracy,liczbaNadgodzin,pensja);
        kontener.drukujListeWyszukanych(p);
    }

    public void zarzadzajWyszukiwaniemStudentow()
    {
        String nazwisko = terminal.wczytajString("Podaj nazwisko (albo wpisz null)");
        String imie = terminal.wczytajString("Podaj imie (albo wpisz null)");
        Integer numerIndeksu = terminal.wczytajInt("Podaj numer (albo wpisz null)");
        Integer rokStudiow = terminal.wczytajInt("Podaj rok studiow (albo wpisz null)");
        String nazwaKursu = terminal.wczytajString("Podaj nazwe kursu (albo wpisz null)");
        System.out.println(" ");
        List<Student> s = kontener.wyszukajStudenta(nazwisko,imie,numerIndeksu,rokStudiow,nazwaKursu);
        kontener.drukujListeWyszukanych(s);
    }
    public void zarzadzajWyszukiwaniemKursow()
    {
        String nazwa = terminal.wczytajString("Podaj nazwę kursu (albo wpisz null)");
        String prowadzacy = terminal.wczytajString("Podaj nazwisko prowadzacego (albo wpisz null)");
        Integer ECTS = terminal.wczytajInt("Podaj liczbe ectsów (albo wpisz null)");
        System.out.printf(" ");
        List<Kurs> k = kontener.wyszukajKurs(nazwa, prowadzacy, ECTS);
        kontener.drukujKursy(k);
    }
    public void zarzadzajUsuwaniemDuplikatow()
    {
        kontener.usunDuplikaty();
    }

    public void zarzadzajZapisaniem(){
        int wybor = terminal.wybierzOpcjePlikow();
        if (wybor == 0);
        switch (wybor) {
            case 1 -> menager.wczytajBazeZTXT("baza.txt");
            case 2 -> menager.zapiszTXT(kontener, "uczelnia_baza.txt");
            case 3 -> menager.wczytajBaze();
            case 4 -> menager.zapiszBaze(kontener);
            case 5 -> {
                menager.zapiszBaze(kontener);
                menager.zapiszTXT(kontener, "uczelnia_baza.txt");
            }
        }
    }
}