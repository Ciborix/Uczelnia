package kontroler;

import gui.dodawanie.DodawaniePracownikaBD;
import gui.tabele.PanelPracownikowBD;
import gui.usuwanie.DialogUsuwania;
import gui.wyszukiwanie.DialogWyszukiwaniaPracownika;
import model.Dane.KontenerDanych;
import model.obserwator.IObserwator;
import model.osoba.pracownik.PracownikBadawczoDydaktyczny;
import model.strategia.BonusBadawczy;

import javax.swing.*;
import java.util.List;

public class KontrolerPracownikowBD implements IObserwator {
    private PanelPracownikowBD panelPracownikowBD;
    private KontenerDanych model;
    private JFrame parentFrame;

    public KontrolerPracownikowBD(PanelPracownikowBD pracownikowBD, KontenerDanych model, JFrame parentFrame) {
        this.panelPracownikowBD = pracownikowBD;
        this.model = model;
        this.parentFrame = parentFrame;

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
            dane[i][4] = pa.getPlec().equalsIgnoreCase("Mężczyzna") ? "Mężczyzna" : "Kobieta";
            dane[i][5] = pa.getStanowisko();
            dane[i][6] = pa.getStazPracy();
            dane[i][7] = pa.getPensja();
            dane[i][8] = pa.getLiczbaPublikacji();
            dane[i][9] = pa.getSumaWyplaty() + "zł";

        }
        panelPracownikowBD.ustawDane(dane);
    }

    public void otworzFormularz() {
        DodawaniePracownikaBD dialog = new DodawaniePracownikaBD(parentFrame);
        dialog.getBtnZapisz().addActionListener(e -> wykonajZapis(dialog));
        dialog.setVisible(true);
    }

    private void wykonajZapis(DodawaniePracownikaBD d) {
        try {

            double pensja = Double.parseDouble(d.getPensjaStr());
            double staz = Double.parseDouble(d.getStazStr());
            int wiek = Integer.parseInt(d.getWiekStr());
            int publikacje = Integer.parseInt(d.getPublikacje());


            if (staz > (wiek - 18)) {
                throw new ValidationException("Staż pracy nie może być dłuższy niż wiek dorosły pracownika!");
            }
            if (pensja < 0) throw new ValidationException("Pensja nie może być ujemna!");
            if (publikacje < 0) throw new ValidationException("Publikacje nie mogą byc liczba ujemna!");

            PracownikBadawczoDydaktyczny pa = new PracownikBadawczoDydaktyczny(
                    d.getImie(), d.getNazwisko(), d.getPesel(), wiek, d.getPlec(),
                    d.getStanowisko(), staz, pensja, publikacje
            );


            pa.setBonusStategia(new BonusBadawczy());


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
        DialogUsuwania dialog = new DialogUsuwania(parentFrame, "Usuwanie Pracownika Administracyjnego", kryteria);
        dialog.setVisible(true);

        if (dialog.isZatwierdzono()) {
            model.usunPracownika(dialog.getWybranyIndeksOpcji(), dialog.getWartosc());
        }
    }


    public void szukajPracownika() {
        DialogWyszukiwaniaPracownika d = new DialogWyszukiwaniaPracownika(parentFrame);
        d.setVisible(true);

        if (d.isZatwierdzono()) {
            var wyniki = model.wyszukajPracownika(
                    d.getNazwisko(), d.getImie(), d.getStanowisko(),
                    d.getStaz(), null, d.getPensja()
            );

            List<PracownikBadawczoDydaktyczny> lista = wyniki.stream()
                    .filter(p -> p instanceof PracownikBadawczoDydaktyczny)
                    .map(p -> (PracownikBadawczoDydaktyczny)p)
                    .toList();

            wyswietlWynikiWyszukiwania(lista);
        }
    }

    private void wyswietlWynikiWyszukiwania(List<PracownikBadawczoDydaktyczny> lista) {
        Object[][] dane = new Object[lista.size()][10];
        for(int i = 0; i < lista.size(); i++) {
            var pb = lista.get(i);
            dane[i][0] = pb.getImie();
            dane[i][1] = pb.getNazwisko();
            dane[i][2] = pb.getPesel();
            dane[i][3] = pb.getWiek();
            dane[i][4] = pb.getPlec().equalsIgnoreCase("Mężczyzna") ? "Mężczyzna" : "Kobieta";
            dane[i][5] = pb.getStanowisko();
            dane[i][6] = pb.getStazPracy();
            dane[i][7] = pb.getPensja();
            dane[i][8] = pb.getLiczbaPublikacji();
            dane[i][9] = pb.getSumaWyplaty() + "zł";
        }
        panelPracownikowBD.ustawDane(dane);
    }
}
