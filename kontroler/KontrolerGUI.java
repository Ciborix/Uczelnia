package kontroler;

import gui.OknoGlowne;
import gui.tabele.PanelKursow;
import gui.tabele.PanelPracownikowAdministracyjnych;
import gui.tabele.PanelPracownikowBD;
import gui.tabele.PanelStudentow;
import model.Dane.KontenerDanych;
import model.Dane.ManagerDanych;
import model.comparator.Kursy.PoEctsNazwiskuProw;
import model.comparator.Osoba.PoNazwisku;
import model.comparator.Osoba.PoNazwiskuImieniu;
import model.comparator.Osoba.PoNazwiskuWieku;

import javax.swing.*;
import java.awt.*;

public class KontrolerGUI {
    private OknoGlowne okno;
    private KontenerDanych model;
    private ManagerDanych manager;

    private KontrolerStudentow kontrolerStudentow;
    private KontrolerPracownikowA kontrolerPracownikowA;
    private KontrolerPracownikowBD kontrolerPracownikowBD;
    private KontrolerKursow kontrolerKursow;

    public KontrolerGUI(OknoGlowne okno, KontenerDanych model, ManagerDanych manager) {
        this.okno = okno;
        this.model = model;
        this.manager = manager;

        JPanel kontenerPionowy = new JPanel();
        kontenerPionowy.setLayout(new BoxLayout(kontenerPionowy, BoxLayout.Y_AXIS));

        PanelStudentow panelStudentow = new PanelStudentow();
        this.kontrolerStudentow = new KontrolerStudentow(panelStudentow, model, okno);

        PanelPracownikowAdministracyjnych panelPracownikowAdministracyjnych = new PanelPracownikowAdministracyjnych();
        this.kontrolerPracownikowA = new KontrolerPracownikowA(panelPracownikowAdministracyjnych, model);

        PanelPracownikowBD panelPracownikowBD = new PanelPracownikowBD();
        this.kontrolerPracownikowBD = new KontrolerPracownikowBD(panelPracownikowBD,model);

        PanelKursow panelKursow = new PanelKursow();
        this.kontrolerKursow = new KontrolerKursow(panelKursow, model);


        panelStudentow.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPracownikowAdministracyjnych.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPracownikowBD.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelKursow.setAlignmentX(Component.LEFT_ALIGNMENT);

        kontenerPionowy.add(panelStudentow);
        kontenerPionowy.add(panelPracownikowAdministracyjnych);
        kontenerPionowy.add(panelPracownikowBD);
        kontenerPionowy.add(panelKursow);

        kontenerPionowy.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(kontenerPionowy);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.okno.add(scrollPane, BorderLayout.CENTER);
        okno.ustawMenu(stworzPasekMenu());
    }

    private JMenuBar stworzPasekMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuPlik = new JMenu("Plik");
        JMenuItem zapisz = new JMenuItem("Zapisz");
        JMenuItem wyjscie = new JMenuItem("Wyjscie");

        JMenu menuSpecjalne = new JMenu("Specjalne");
        JMenuItem pokazKursy = new JMenuItem("Pokaz Kursy danej osoby");
        JMenuItem dodajKursdoStudenta = new JMenuItem("Dodaj Kurs do wskazanej osoby");
        JMenuItem usunDuplikaty = new JMenuItem("Usun Duplikaty");

        JMenu menuDodaj = new JMenu("Dodaj");
        JMenuItem student = new JMenuItem("Student");
        JMenuItem pracownikA = new JMenuItem("Pracownik Administracyjny");
        JMenuItem pracownikBD = new JMenuItem("Pracownik Badawczo Dydaktyczny");
        JMenuItem kursow = new JMenuItem("Kursy");

        JMenu menuUsun = new JMenu("Usun");
        JMenuItem studentdel = new JMenuItem("Usun studenta");
        JMenuItem pracownikAdel = new JMenuItem("Usun Pracownika Administracyjnego");
        JMenuItem pracownikBDdel = new JMenuItem("Usun Pracownika Badawczo Dydaktycznego");
        JMenuItem kursdel = new JMenuItem("Usun Kurs");


        JMenu menuWyszukwanie = new JMenu("Wyszukiwanie");
        JMenuItem wyszukajStudenta = new JMenuItem("Wyszukaj studenta");
        JMenuItem wyszukajPracownikaA = new JMenuItem("Wyszukaj Pracownika Administracyjnego");
        JMenuItem wyszukaPracownikaBD = new JMenuItem("Wyszukaj Pracownika Badawczo Dydaktycznego");
        JMenuItem wyszukajKurs = new  JMenuItem("Wyszukaj Kurs");
        JMenuItem pokazWszystko = new JMenuItem("Pokaz wszystko - zdejmij filtry");

        zapisz.addActionListener(e -> manager.zapiszBaze(model));
        wyjscie.addActionListener(e-> System.exit(  0));

        pokazKursy.addActionListener(e -> kontrolerStudentow.akcjaPokazKursy());
        dodajKursdoStudenta.addActionListener(e-> kontrolerStudentow.przypiszKurs());
        usunDuplikaty.addActionListener(e-> usunDuplikaty());

        student.addActionListener(e -> kontrolerStudentow.otworzFormularzDodawania());
        pracownikA.addActionListener(e-> kontrolerPracownikowA.otworzFormularz());
        pracownikBD.addActionListener(e-> kontrolerPracownikowBD.otworzFormularz());
        kursow.addActionListener(e-> kontrolerKursow.dodajKurs());

        studentdel.addActionListener(e-> kontrolerStudentow.usunStudenta());
        pracownikAdel.addActionListener(e-> kontrolerPracownikowA.usunPracownika());
        pracownikBDdel.addActionListener(e-> kontrolerPracownikowBD.usunPracownika());
        kursdel.addActionListener(e-> kontrolerKursow.usunKurs());




        wyszukajStudenta.addActionListener(e->kontrolerStudentow.wyszukajStudenta());
        wyszukajPracownikaA.addActionListener(e->kontrolerPracownikowA.szukajPracownika());
        wyszukaPracownikaBD.addActionListener(e->kontrolerPracownikowBD.szukajPracownika());
        wyszukajKurs.addActionListener(e->kontrolerKursow.szukajKursow());
        pokazWszystko.addActionListener(e->pokazWszystkich());

        menuPlik.add(zapisz);
        menuPlik.addSeparator();
        menuPlik.add(wyjscie);

        menuDodaj.add(student);
        menuDodaj.addSeparator();
        menuDodaj.add(pracownikA);
        menuDodaj.addSeparator();
        menuDodaj.add(pracownikBD);
        menuDodaj.addSeparator();
        menuDodaj.add(kursow);


        menuSpecjalne.add(pokazKursy);
        menuSpecjalne.addSeparator();
        menuSpecjalne.add(dodajKursdoStudenta);
        menuSpecjalne.addSeparator();
        menuSpecjalne.add(usunDuplikaty);


        menuUsun.add(studentdel);
        menuUsun.addSeparator();
        menuUsun.add(pracownikAdel);
        menuUsun.addSeparator();
        menuUsun.add(pracownikBDdel);
        menuUsun.addSeparator();
        menuUsun.add(kursdel);


        menuWyszukwanie.add(wyszukajStudenta);
        menuWyszukwanie.addSeparator();
        menuWyszukwanie.add(wyszukajPracownikaA);
        menuWyszukwanie.addSeparator();
        menuWyszukwanie.add(wyszukaPracownikaBD);
        menuWyszukwanie.addSeparator();
        menuWyszukwanie.add(wyszukajKurs);
        menuWyszukwanie.addSeparator();
        menuWyszukwanie.add(pokazWszystko);

        menuBar.add(menuPlik);
        menuBar.add(menuSpecjalne);
        menuBar.add(menuDodaj);
        menuBar.add(menuUsun);
        Sortowanie menuSortowania = new Sortowanie(model);
        menuBar.add(menuSortowania.stworzMenuSortowania());
        menuBar.add(menuWyszukwanie);

        return menuBar;
    }

    public void usunDuplikaty() {
        int rozmiarPrzed = model.getOsoby().size();

        model.usunDuplikaty();

        int rozmiarPo = model.getOsoby().size();
        int usunieto = rozmiarPrzed - rozmiarPo;

        if (usunieto > 0) {
            JOptionPane.showMessageDialog(okno,
                    "Czyszczenie zakończone!\nUsunięto duplikaty w liczbie: " + usunieto,
                    "Porządki w bazie",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(okno,
                    "Baza jest czysta. Nie znaleziono żadnych duplikatów.",
                    "Informacja",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void pokazWszystkich() {

        kontrolerStudentow.odswierzTabeleStudentow();
        kontrolerPracownikowA.odswiezTabelePracownikowA();
        kontrolerPracownikowBD.odswiezTabelePracownikowPB();
        kontrolerKursow.odswiez();

        System.out.println("[INFO] Filtry zdjęte. Wyświetlam pełną bazę danych.");
    }

}
