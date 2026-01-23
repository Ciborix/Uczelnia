package app;


import model.Dane.KontenerDanych;
import model.Dane.MenagerDanych;
import model.IOterminal.IOTerminal;
import model.Kontroler.Kontroler;
import model.obserwator.KonsolaInformator;
import model.obserwator.MonitorStanu;

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