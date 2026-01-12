package uczelnia.app;

import uczelnia.Dane.KontenerDanych;
import uczelnia.MenagerDanych;
import uczelnia.IOTerminal;
import uczelnia.Kontroler.Kontroler;
import uczelnia.obserwator.*;

public class UczelniaApp {
    public static void main(String[] args) {
        MenagerDanych manager = new MenagerDanych("uczelnia_baza.ser");
        IOTerminal terminal = new IOTerminal();
        KontenerDanych kontener = manager.wczytajBaze();
        kontener.dodajObserwatora(new KonsolaInformator());
        kontener.dodajObserwatora(new MonitorStanu());
        Kontroler glownyKontroler = new Kontroler(kontener, manager, terminal);
        glownyKontroler.start();
    }
}