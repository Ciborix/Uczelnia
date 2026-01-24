package kontroler;

import gui.PanelPracownikowBD;
import model.Dane.KontenerDanych;
import model.osoba.pracownik.PracownikBadawczoDydaktyczny;
import model.osoba.pracownik.PracownikUczelni;

import java.util.List;

public class KontrolerPracownikowBD {
    private PanelPracownikowBD panelPracownikowBD;
    private KontenerDanych model;

    public KontrolerPracownikowBD(PanelPracownikowBD pracownikowBD, KontenerDanych model) {
        this.panelPracownikowBD = pracownikowBD;
        this.model = model;

        odswiezTabelePracownikowPB();
    }

    public void odswiezTabelePracownikowPB() {
        List<PracownikUczelni> wszyscyPracownicy= model.wyszukajPracownika(null,null,null,null,null,null);

        List<PracownikBadawczoDydaktyczny> tylkoPBD = wszyscyPracownicy.stream()
                .filter(p -> p instanceof PracownikBadawczoDydaktyczny)
                .map(p-> (PracownikBadawczoDydaktyczny)p)
                .toList();

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
}
