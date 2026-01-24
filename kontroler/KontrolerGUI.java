package kontroler;

import gui.OknoGlowne;
import gui.tabele.PanelKursow;
import gui.tabele.PanelPracownikowAdministracyjnych;
import gui.tabele.PanelPracownikowBD;
import gui.tabele.PanelStudentow;
import model.Dane.KontenerDanych;
import model.Dane.ManagerDanych;

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

        JMenu menuDodaj = new JMenu("Dodaj");
        JMenuItem student = new JMenuItem("Student");
        JMenuItem pracownikA = new JMenuItem("Pracownik Administracyjny");
        JMenuItem pracownikBD = new JMenuItem("Pracownik Badawczo Dydaktyczny");
        JMenuItem kursow = new JMenuItem("Kursy");

        zapisz.addActionListener(e -> manager.zapiszBaze(model));
        wyjscie.addActionListener(e-> System.exit(  0));
        pokazKursy.addActionListener(e -> kontrolerStudentow.akcjaPokazKursy());

        student.addActionListener(e -> kontrolerStudentow.otworzFormularzDodawania());
        pracownikA.addActionListener(e-> kontrolerPracownikowA.otworzFormularz());
        pracownikBD.addActionListener(e-> kontrolerPracownikowBD.otworzFormularz());
        kursow.addActionListener(e-> kontrolerKursow.akcjaDodajKurs());

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

        menuBar.add(menuPlik);

        menuSpecjalne.add(pokazKursy);
        menuSpecjalne.addSeparator();
        menuSpecjalne.add(dodajKursdoStudenta);
        menuBar.add(menuSpecjalne);

        menuBar.add(menuDodaj);
        return menuBar;
    }

}
