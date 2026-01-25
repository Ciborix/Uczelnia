package kontroler;

import gui.dodawanie.DodawaniePracownikaBD;
import gui.tabele.PanelPracownikowBD;
import gui.usuwanie.DialogUsuwania;
import model.Dane.KontenerDanych;
import model.obserwator.IObserwator;
import model.osoba.pracownik.PracownikBadawczoDydaktyczny;
import model.strategia.BonusAdministracyjny;

import javax.swing.*;
import java.util.List;

public class KontrolerPracownikowBD implements IObserwator {
    private PanelPracownikowBD panelPracownikowBD;
    private KontenerDanych model;

    public KontrolerPracownikowBD(PanelPracownikowBD pracownikowBD, KontenerDanych model) {
        this.panelPracownikowBD = pracownikowBD;
        this.model = model;

        model.dodajObserwatora(this);

        odswiezTabelePracownikowPB();
    }

    @Override
    public void aktualizuj(String msg, KontenerDanych model)
    {
        SwingUtilities.invokeLater(() ->{
            odswiezTabelePracownikowPB();
        });
    }

    public void odswiezTabelePracownikowPB() {
        List<PracownikBadawczoDydaktyczny> tylkoPBD = model.getPracownicyBadawczy();
        Object[][] dane = new Object[tylkoPBD.size()][10];
        for(int i=0;i<tylkoPBD.size();i++) {
            PracownikBadawczoDydaktyczny pa = tylkoPBD.get(i);
            dane[i][0] = pa.getImie();
            dane[i][1] = pa.getNazwisko();
            dane[i][2] = pa.getPesel();
            dane[i][3] = pa.getWiek();
            dane[i][4] = pa.getPlec().equalsIgnoreCase("M") ? "Mężczyzna" : "Kobieta";
            dane[i][5] = pa.getStanowisko();
            dane[i][6] = pa.getStazPracy();
            dane[i][7] = pa.getPensja();
            dane[i][8] = pa.getLiczbaPublikacji();
            dane[i][9] = pa.getSumaWyplaty() + "zł";

        }
        panelPracownikowBD.ustawDane(dane);
    }

    public void otworzFormularz() {
        DodawaniePracownikaBD dialog = new DodawaniePracownikaBD(null); // lub parentFrame
        dialog.getBtnZapisz().addActionListener(e -> wykonajZapis(dialog));
        dialog.setVisible(true);
    }

    private void wykonajZapis(DodawaniePracownikaBD d) {
        try {
            // 1. Walidacja formatu
            double pensja = Double.parseDouble(d.getPensjaStr());
            double staz = Double.parseDouble(d.getStazStr());
            int wiek = Integer.parseInt(d.getWiekStr());
            int publikacje = Integer.parseInt(d.getPublikacje());

            // 2. Walidacja merytoryczna (Trik z logicznym błędem)
            if (staz > (wiek - 18)) {
                throw new Exception("Staż pracy nie może być dłuższy niż wiek dorosły pracownika!");
            }
            if (pensja < 0) throw new Exception("Pensja nie może być ujemna!");

            // 3. Tworzenie obiektu
            PracownikBadawczoDydaktyczny pa = new PracownikBadawczoDydaktyczny(
                    d.getImie(), d.getNazwisko(), d.getPesel(), wiek, d.getPlec(),
                    d.getStanowisko(), staz, pensja, publikacje
            );

            // 4. PRZYPISANIE STRATEGII (Lista nr 6 - Wzorce)
            pa.setBonusStategia(new BonusAdministracyjny());

            // 5. Dodanie do bazy
            model.dodajOsobe(pa);
            d.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(d, "Błąd formatu liczb!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(d, ex.getMessage());
        }
    }

    public void usunPracownika() {
        String[] kryteria = {"Nazwisko", "Imię", "Staż pracy", "Stanowisko"};
        DialogUsuwania dialog = new DialogUsuwania(null, "Usuwanie Pracownika Administracyjnego", kryteria);
        dialog.setVisible(true);

        if (dialog.isZatwierdzono()) {
            model.usunPracownika(dialog.getWybranyIndeksOpcji(), dialog.getWartosc());
        }
    }
}
