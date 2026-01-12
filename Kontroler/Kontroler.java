package uczelnia.Kontroler;

import uczelnia.Dane.KontenerDanych;
import uczelnia.IOTerminal;
import uczelnia.MenagerDanych;
import uczelnia.KreatorDanych;
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
            case 2 -> zarzadzajDodawaniem();
            case 3 -> zarzadzajUsuwaniem();

            case 10 -> menager.zapiszBaze(kontener);
            case 0 -> czyProgramDziala = false;
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
}