package kontroler;

import gui.DialogKursy;

import gui.dodawanie.DodawanieStudenta;
import gui.tabele.PanelStudentow;
import gui.usuwanie.DialogUsuwania;
import model.Dane.KontenerDanych;
import model.obserwator.IObserwator;
import model.osoba.student.Kurs;
import model.osoba.student.Student;

import javax.swing.*;
import java.util.List;

public class KontrolerStudentow implements IObserwator{
    private PanelStudentow panelStudentow;
    private KontenerDanych model;
    private JFrame parentFrame;

    public KontrolerStudentow(PanelStudentow panelStudentow,KontenerDanych model, JFrame parentFrame) {
        this.panelStudentow = panelStudentow;
        this.model = model;
        this.parentFrame = parentFrame;

        this.model.dodajObserwatora(this);
        odswierzTabeleStudentow();
    }

    @Override
    public void aktualizuj(String msg, KontenerDanych model) {
        SwingUtilities.invokeLater(()-> {
            odswierzTabeleStudentow();
        });
    }

    public void odswierzTabeleStudentow() {
        List<Student> studenci = model.getStudenci();

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

    // Metoda wywoływana np. z przycisku w menu głównym
    public void otworzFormularzDodawania() {
        List<Kurs> kursyZModelu = model.getKursy();
        DodawanieStudenta dialog = new DodawanieStudenta(parentFrame, kursyZModelu);

        dialog.getBtnZapisz().addActionListener(e -> wykonajZapis(dialog));
        dialog.setVisible(true);
    }

    private void wykonajZapis(DodawanieStudenta dialog) {
        try {
            // 1. Walidacja i tworzenie obiektu
            Student nowy = walidujITworz(dialog);

            // 2. Dodanie do modelu -> to odpali powiadom()
            model.dodajOsobe(nowy);

            // 3. Sukces - zamykamy okno
            dialog.dispose();

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Błąd walidacji", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Wiek i Indeks muszą być liczbami!", "Błąd formatu", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Student walidujITworz(DodawanieStudenta d) throws ValidationException {
        // Pobieranie danych
        String imie = d.getImie().trim();
        String nazwisko = d.getNazwisko().trim();
        int wiek = Integer.parseInt(d.getWiekStr());
        int indeks = Integer.parseInt(d.getIndeksStr());
        int rok = d.getRok();
        boolean s1 = d.isStopien1();

        // WALIDACJA MERYTORYCZNA
        if (imie.isEmpty() || nazwisko.isEmpty()) throw new ValidationException("Uzupełnij imię i nazwisko!");
        if (wiek < 18 || wiek > 100) throw new ValidationException("Niepoprawny wiek (18-100)!");
        if (s1 && rok > 4) throw new ValidationException("I stopień to max 4 rok!");
        if (!s1 && rok > 2) throw new ValidationException("II stopień to max 2 rok!");

        // Zwracamy gotowy obiekt
        return new Student(
                imie, nazwisko, d.getPesel(), wiek, d.getPlec(),
                indeks, rok, new java.util.ArrayList<>(d.getWybraneKursy()),
                d.isErasmus(), s1, !s1, d.isStacjonarny(), !d.isStacjonarny()
        );
    }

    public void usunStudenta() {
        String[] kryteria = {"Nazwisko", "Imię", "Numer Indeksu", "Rok Studiów"};
        DialogUsuwania dialog = new DialogUsuwania(parentFrame, "Usuwanie Studenta", kryteria);
        dialog.setVisible(true);

        if (dialog.isZatwierdzono()) {
            // Wywołujemy Twoją metodę z modelu
            model.usunStudenta(dialog.getWybranyIndeksOpcji(), dialog.getWartosc());
            // Dzięki Obserwatorowi tabela odświeży się sama!
        }
    }
}
