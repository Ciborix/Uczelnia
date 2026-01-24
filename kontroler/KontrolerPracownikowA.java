package kontroler;

import gui.PanelPracownikowAdministracyjnych;
import model.Dane.KontenerDanych;
import model.Kontroler.Kontroler;
import model.osoba.pracownik.PracownikAdministracyjny;
import model.osoba.pracownik.PracownikUczelni;

import java.util.List;

public class KontrolerPracownikowA {
    private PanelPracownikowAdministracyjnych panelPracownikowAdministracyjnych;
    private KontenerDanych model;

    public KontrolerPracownikowA(PanelPracownikowAdministracyjnych pa, KontenerDanych model) {
        this.panelPracownikowAdministracyjnych = pa;
        this.model = model;

        odswiezTabelePracownikowA();
    }

    public void odswiezTabelePracownikowA() {
        List<PracownikUczelni> wszyscyPracownicy= model.wyszukajPracownika(null,null,null,null,null,null);

        List<PracownikAdministracyjny> tylkoPA = wszyscyPracownicy.stream()
                .filter(p -> p instanceof PracownikAdministracyjny)
                .map(p-> (PracownikAdministracyjny)p)
                .toList();

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
}
