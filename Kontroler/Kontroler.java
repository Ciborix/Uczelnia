package uczelnia.Kontroler;

import uczelnia.Dane.KontenerDanych;
import uczelnia.IOTerminal;
import uczelnia.MenagerDanych;
import uczelnia.KreatorDanych;
import uczelnia.comparator.Kursy.PoEctsNazwiskuProw;
import uczelnia.comparator.Osoba.PoNazwisku;
import uczelnia.comparator.Osoba.PoNazwiskuImieniu;
import uczelnia.comparator.Osoba.PoNazwiskuWieku;
import uczelnia.obserwator.KonsolaInformator;
import uczelnia.obserwator.MonitorStanu;

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
        kontener.dodajObserwatora(new MonitorStanu());
        kontener.dodajObserwatora(new KonsolaInformator());
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

            case 10 -> menager.zapiszBaze(kontener);
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
        int co = terminal.ask("1. Student | 2. Pracownik Badawczy | 3. Pracownik Adm");
        switch (co) {
            case 1 -> kontener.dodajOsobe(kreator.utworzStudenta(scanner));
            case 2 -> kontener.dodajOsobe(kreator.utworzPracownikaBadawczoDydaktycznego(scanner));
            case 3 -> kontener.dodajOsobe(kreator.utworzPracownikaAdministracyjnego(scanner));
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
            String val = terminal.wczytajString(scanner, "Podaj szukaną wartość");
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
}