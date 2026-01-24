package kontroler;

import gui.dodawanie.DodawanieKurs;

import gui.tabele.PanelKursow;
import model.Dane.KontenerDanych;
import model.obserwator.IObserwator;
import model.osoba.student.Kurs;
import javax.swing.*;
import java.util.List;

public class KontrolerKursow implements IObserwator {
    private PanelKursow panel;
    private KontenerDanych model;

    public KontrolerKursow(PanelKursow panel, KontenerDanych model) {
        this.panel = panel;
        this.model = model;
        this.model.dodajObserwatora(this); // Rejestracja!
        odswiez();
    }

    @Override
    public void aktualizuj(String msg, KontenerDanych kontener) {
        SwingUtilities.invokeLater(this::odswiez);
    }

    public void odswiez() {
        List<Kurs> kursy = model.getKursy();
        Object[][] dane = new Object[kursy.size()][3];
        for (int i = 0; i < kursy.size(); i++) {
            dane[i][0] = kursy.get(i).getNazwa();
            dane[i][1] = kursy.get(i).getProwadzacy();
            dane[i][2] = kursy.get(i).getPunktyECTS();
        }
        panel.ustawDane(dane);
    }

    public void akcjaDodajKurs() {
        DodawanieKurs dialog = new DodawanieKurs(null);
        dialog.setVisible(true);

        if (dialog.isZatwierdzono()) {
            try {
                // Wyprowadzamy szukaną na lewą stronę
                String nazwa = dialog.getNazwa();
                String prowadzacy = dialog.getProwadzacy();
                int ects = Integer.parseInt(dialog.getEctsRaw());

                // Tworzymy obiekt - trik: używamy Twojego konstruktora
                Kurs nowyKurs = new Kurs(nazwa, prowadzacy, ects);

                // Dodajemy do bazy
                model.dodajKurs(nowyKurs);

                // Ostrzeżenie: Twoje tabele w BoxLayout muszą wiedzieć o zmianie!
                // Jeśli masz panel wyświetlający same kursy, wywołaj jego odświeżenie.
                JOptionPane.showMessageDialog(null, "Dodano kurs: " + nazwa);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Błąd: ECTS musi być liczbą całkowitą!");
            }
        }
    }
}