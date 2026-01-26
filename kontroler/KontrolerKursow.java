package kontroler;

import gui.dodawanie.DodawanieKurs;

import gui.tabele.PanelKursow;
import gui.usuwanie.DialogUsuwania;
import gui.wyszukiwanie.DialogWyszukiwaniaKursow;
import model.Dane.KontenerDanych;
import model.obserwator.IObserwator;
import model.osoba.student.Kurs;
import javax.swing.*;
import java.util.List;

public class KontrolerKursow implements IObserwator {
    private PanelKursow panel;
    private KontenerDanych model;
    private JFrame parentFrame;

    public KontrolerKursow(PanelKursow panel, KontenerDanych model, JFrame parentFrame) {
        this.panel = panel;
        this.model = model;
        this.parentFrame = parentFrame;

        this.model.dodajObserwatora(this);
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

    public void dodajKurs() {
        DodawanieKurs dialog = new DodawanieKurs(parentFrame);
        dialog.setVisible(true);

        if (dialog.isZatwierdzono()) {
            try {

                String nazwa = dialog.getNazwa();
                if (nazwa.isEmpty()) throw new ValidationException("Nazwa kursu nie może być pusta!");
                String prowadzacy = dialog.getProwadzacy();
                int ects = Integer.parseInt(dialog.getEctsRaw());
                if (ects < 0 || ects > 30) throw new ValidationException("Punkty ECTS maja być w przedziale 0-30!");

                Kurs nowyKurs = new Kurs(nazwa, prowadzacy, ects);
                model.dodajKurs(nowyKurs);

                JOptionPane.showMessageDialog(parentFrame, "Dodano kurs: " + nazwa);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parentFrame,e.getMessage(), "Błąd: ECTS musi być liczbą całkowitą!",JOptionPane.WARNING_MESSAGE);
            } catch (ValidationException e) {
                JOptionPane.showMessageDialog(parentFrame, "ECTS musza byc liczba!","Bład", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void usunKurs() {
        String[] opcje = {"Nazwa kursu", "Prowadzący", "Punkty ECTS"};

        DialogUsuwania dialog = new DialogUsuwania(parentFrame, "Usuwanie Kursu", opcje);
        dialog.setVisible(true);

        if (dialog.isZatwierdzono()) {
            model.usunKurs(dialog.getWybranyIndeksOpcji(), dialog.getWartosc());

        }
    }

    public void szukajKursow() {
        DialogWyszukiwaniaKursow d = new DialogWyszukiwaniaKursow(parentFrame);
        d.setVisible(true);

        if (d.isZatwierdzono()) {
            var wyniki = model.wyszukajKurs(
                    d.getNazwa(),
                    d.getProwadzacy(),
                    d.getEcts()
            );

            wyswietlWynikiWyszukiwania(wyniki);
        }
    }

    private void wyswietlWynikiWyszukiwania(List<Kurs> lista) {

        Object[][] dane = new Object[lista.size()][3];
        for(int i = 0; i < lista.size(); i++) {
            var k = lista.get(i);
            dane[i][0] = k.getNazwa();
            dane[i][1] = k.getProwadzacy();
            dane[i][2] = k.getPunktyECTS();
        }
        panel.ustawDane(dane);
    }
}