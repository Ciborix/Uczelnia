package uczelnia.app;

import uczelnia.Dane.KontenerDanych;
import uczelnia.MenagerDanych;
import uczelnia.IOTerminal;
import uczelnia.Kontroler.Kontroler;
import uczelnia.obserwator.*;

public class UczelniaApp {
    public static void main(String[] args) {
        // 1. Inicjalizacja zarządzania danymi
        MenagerDanych manager = new MenagerDanych("uczelnia_baza.ser");
        IOTerminal terminal = new IOTerminal();

        // 2. Wczytanie bazy
        KontenerDanych kontener = manager.wczytajBaze();

        // 3. Rejestracja obserwatorów (Logowanie, Stan, Auto-zapis)
        kontener.dodajObserwatora(new KonsolaInformator());
        kontener.dodajObserwatora(new MonitorStanu());

        // 4. Start kontrolera
        Kontroler glownyKontroler = new Kontroler(kontener, manager, terminal);
        glownyKontroler.start();
    }
}