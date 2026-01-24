package app;

import kontroler.KontrolerGUI;
import model.Dane.KontenerDanych;
import model.Dane.ManagerDanych;
import gui.OknoGlowne;
import javax.swing.SwingUtilities;

public class UczelniaApp {
    public static void main(String[] args) {
        ManagerDanych manager = new ManagerDanych("uczelnia_baza.ser");
        KontenerDanych kontener = manager.wczytajBaze();

        SwingUtilities.invokeLater(() -> {
            OknoGlowne okno = new OknoGlowne();
            new KontrolerGUI(okno, kontener, manager);
            okno.setVisible(true);
        });
    }
}