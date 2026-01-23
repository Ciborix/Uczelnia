package app;


import Dane.KontenerDanych;
import Dane.MenagerDanych;
import IOterminal.IOTerminal;
import Kontroler.Kontroler;
import obserwator.KonsolaInformator;
import obserwator.MonitorStanu;

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