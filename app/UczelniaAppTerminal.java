package app;


import model.Dane.KontenerDanych;
import model.Dane.ManagerDanych;
import model.IOterminal.IOTerminal;
import model.Kontrolertermianal.KontrolerTerminal;
import model.obserwator.KonsolaInformator;
import model.obserwator.MonitorStanu;

public class UczelniaAppTerminal {
    public static void main(String[] args) {
        ManagerDanych manager = new ManagerDanych("uczelnia_baza.ser");
        IOTerminal terminal = new IOTerminal();
        KontenerDanych kontener = manager.wczytajBaze();
        kontener.dodajObserwatora(new KonsolaInformator());
        kontener.dodajObserwatora(new MonitorStanu());
        KontrolerTerminal glownyKontrolerTerminal = new KontrolerTerminal(kontener, manager, terminal);
        glownyKontrolerTerminal.start();
    }
}