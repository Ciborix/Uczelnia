package kontroler;

import gui.DialogKursy;
import gui.PanelStudentow;
import model.Dane.KontenerDanych;
import model.osoba.student.Kurs;
import model.osoba.student.Student;

import javax.swing.*;
import java.util.List;

public class KontrolerStudentow {
    private PanelStudentow panelStudentow;
    private KontenerDanych model;
    private JFrame parentFrame;

    public KontrolerStudentow(PanelStudentow panelStudentow,KontenerDanych model, JFrame parentFrame) {
        this.panelStudentow = panelStudentow;
        this.model = model;
        this.parentFrame = parentFrame;

        odswierzTabeleStudentow();
    }

    public void odswierzTabeleStudentow() {
        List<Student> studenci = model.wyszukajStudenta(null,null,null,null,null);

        Object[][] dane = new Object[studenci.size()][10];
        for(int i = 0; i < studenci.size(); i++) {
            Student s = (Student)studenci.get(i);
            dane[i][0] = s.getImie();
            dane[i][1] = s.getNazwisko();
            dane[i][2] = s.getPesel();
            dane[i][3] = s.getWiek();
            dane[i][4] = s.getPlec().equalsIgnoreCase("M") ? "Mężczyzna" : "Kobieta";
            dane[i][5] = s.getNrIndeks();
            dane[i][6] = s.getRokStudiow();
            dane[i][7] = s.isCzyERASMUS() ? "Tak": "Nie";
            dane[i][8] = s.isCzyIStopien() ? "1 stopień": "2 stopień";
            dane[i][9] = s.isCzyStacjonarny() ? "Stacjonarnie" : "Niestacjonarnie";

        }
        panelStudentow.ustawDane(dane);
    }

    public void akcjaPokazKursy() {
        int row = panelStudentow.getTabela().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(parentFrame, "Zaznacz studenta!");
            return;
        }
        int nrIndeks = (int)panelStudentow.getTabela().getValueAt(row, 5);
        List<Student> wynik = model.wyszukajStudenta(null,null,nrIndeks,null,null);

        if (!wynik.isEmpty()) {
            Student s =wynik.get(0);
            List<Kurs> k = s.getKursyList();

            if (k == null ||  k.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Ten student nie ma przypisanych kursow!");
                return;
            }
            Object[][] daneDlaTabeli = przygotujDaneKursow(k);

            DialogKursy dialog = new DialogKursy(parentFrame, "Kursy studenta: " + s.getNazwisko());
            dialog.zaladujTabele(daneDlaTabeli);
            dialog.setVisible(true);
        }
    }
    public Object[][] przygotujDaneKursow(List<Kurs> k) {
        Object[][] daneDlaTabeli = new Object[k.size()][3];
        for (int i = 0; i < k.size(); i++) {
            Kurs kurs = k.get(i);
            daneDlaTabeli[i][0]= kurs.getNazwa();
            daneDlaTabeli[i][1]= kurs.getProwadzacy();
            daneDlaTabeli[i][2]= kurs.getPunktyECTS();
        }
        return daneDlaTabeli;
    }
}
