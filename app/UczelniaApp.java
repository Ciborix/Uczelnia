package app;

import kontroler.KontrolerGUI;
import model.Dane.KontenerDanych;
import model.Dane.ManagerDanych;
import gui.OknoGlowne;
import model.obserwator.KonsolaInformator;
import model.obserwator.MonitorStanu;

import javax.swing.SwingUtilities;

public class UczelniaApp {
    public static void main(String[] args) {
        ManagerDanych manager = new ManagerDanych("uczelnia_baza.ser");
        KontenerDanych kontener = manager.wczytajBaze();

        kontener.dodajObserwatora(new KonsolaInformator());
        kontener.dodajObserwatora(new MonitorStanu());

        SwingUtilities.invokeLater(() -> {
            OknoGlowne okno = new OknoGlowne();
            new KontrolerGUI(okno, kontener, manager);
            okno.setVisible(true);
        });
    }
}