package kontroler;

import gui.dodawanie.DodawaniePracownikaA;
import gui.tabele.PanelPracownikowAdministracyjnych;
import gui.usuwanie.DialogUsuwania;
import gui.wyszukiwanie.DialogWyszukiwaniaPracownika;
import model.Dane.KontenerDanych;
import model.obserwator.IObserwator;
import model.osoba.pracownik.PracownikAdministracyjny;
import model.strategia.BonusAdministracyjny;

import javax.swing.*;
import java.util.List;

public class KontrolerPracownikowA implements IObserwator {
    private PanelPracownikowAdministracyjnych panelPracownikowAdministracyjnych;
    private KontenerDanych model;

    public KontrolerPracownikowA(PanelPracownikowAdministracyjnych pa, KontenerDanych model) {
        this.panelPracownikowAdministracyjnych = pa;
        this.model = model;

        this.model.dodajObserwatora(this);

        odswiezTabelePracownikowA();
    }

    @Override
    public void aktualizuj(String msg, KontenerDanych model)
    {
        SwingUtilities.invokeLater(() ->{
           odswiezTabelePracownikowA();
        });
    }

    public void odswiezTabelePracownikowA() {
        List <PracownikAdministracyjny> tylkoPA= model.getPracownicyAdministracyjni();

        Object[][] dane = new Object[tylkoPA.size()][10];
        for(int i=0;i<tylkoPA.size();i++) {
            PracownikAdministracyjny pa = tylkoPA.get(i);
            dane[i][0] = pa.getImie();
            dane[i][1] = pa.getNazwisko();
            dane[i][2] = pa.getPesel();
            dane[i][3] = pa.getWiek();
            dane[i][4] = pa.getPlec().equalsIgnoreCase("M") ? "Mężczyzna" : "Kobieta";
            dane[i][5] = pa.getStanowisko();
            dane[i][6] = pa.getStazPracy();
            dane[i][7] = pa.getPensja();
            dane[i][8] = pa.getLiczbNadgodzin();
            dane[i][9] = pa.getSumaWyplaty() + "zł";

        }
        panelPracownikowAdministracyjnych.ustawDane(dane);
    }

    public void otworzFormularz() {
        DodawaniePracownikaA dialog = new DodawaniePracownikaA(null);
        dialog.getBtnZapisz().addActionListener(e -> wykonajZapis(dialog));
        dialog.setVisible(true);
    }

    private void wykonajZapis(DodawaniePracownikaA d) {
        try {

            double pensja = Double.parseDouble(d.getPensjaStr());
            double staz = Double.parseDouble(d.getStazStr());
            int wiek = Integer.parseInt(d.getWiekStr());
            int nadgodziny = Integer.parseInt(d.getNadgodzinyStr());


            if (staz > (wiek - 18)) {
                throw new Exception("Staż pracy nie może być dłuższy niż wiek dorosły pracownika!");
            }
            if (pensja < 0) throw new Exception("Pensja nie może być ujemna!");


            PracownikAdministracyjny pa = new PracownikAdministracyjny(
                    d.getImie(), d.getNazwisko(), d.getPesel(), wiek, d.getPlec(),
                    d.getStanowisko(), staz, pensja, nadgodziny
            );

            pa.setBonusStategia(new BonusAdministracyjny());


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

    public void szukajPracownika() {
        DialogWyszukiwaniaPracownika d = new DialogWyszukiwaniaPracownika(null);
        d.setVisible(true);

        if (d.isZatwierdzono()) {
            var wyniki = model.wyszukajPracownika(
                    d.getNazwisko(), d.getImie(), d.getStanowisko(),
                    d.getStaz(), d.getNadgodziny(), d.getPensja()
            );

            List<PracownikAdministracyjny> lista = wyniki.stream()
                    .filter(p -> p instanceof PracownikAdministracyjny)
                    .map(p -> (PracownikAdministracyjny)p)
                    .toList();

            wyswietlWynikiWyszukiwania(lista);
        }
    }

    private void wyswietlWynikiWyszukiwania(List<PracownikAdministracyjny> lista) {
        Object[][] dane = new Object[lista.size()][10];
        for(int i = 0; i < lista.size(); i++) {
            var pa = lista.get(i);
            dane[i][0] = pa.getImie();
            dane[i][1] = pa.getNazwisko();
            dane[i][2] = pa.getPesel();
            dane[i][3] = pa.getWiek();
            dane[i][4] = pa.getPlec().equalsIgnoreCase("M") ? "Mężczyzna" : "Kobieta";
            dane[i][5] = pa.getStanowisko();
            dane[i][6] = pa.getStazPracy();
            dane[i][7] = pa.getPensja();
            dane[i][8] = pa.getLiczbNadgodzin();
            dane[i][9] = pa.getSumaWyplaty() + "zł";
        }
        panelPracownikowAdministracyjnych.ustawDane(dane);
    }
}
