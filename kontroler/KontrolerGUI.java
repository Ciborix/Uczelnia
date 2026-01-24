package kontroler;

import gui.OknoGlowne;
import gui.PanelPracownikowAdministracyjnych;
import gui.PanelPracownikowBD;
import gui.PanelStudentow;
import model.Dane.KontenerDanych;
import model.Dane.ManagerDanych;
import model.osoba.student.Kurs;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KontrolerGUI {
    private OknoGlowne okno;
    private KontenerDanych model;
    private ManagerDanych manager;

    private KontrolerStudentow kontrolerStudentow;
    private KontrolerPracownikowA kontrolerPracownikowA;
    private KontrolerPracownikowBD kontrolerPracownikowBD;

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

        panelStudentow.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPracownikowAdministracyjnych.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPracownikowBD.setAlignmentX(Component.LEFT_ALIGNMENT);

        kontenerPionowy.add(panelStudentow);
        kontenerPionowy.add(panelPracownikowAdministracyjnych);
        kontenerPionowy.add(panelPracownikowBD);

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

        JMenuItem kursy = new JMenuItem("Kursy");

        kursy.addActionListener(e -> kontrolerStudentow.akcjaPokazKursy());
        zapisz.addActionListener(e -> manager.zapiszBaze(model));
        wyjscie.addActionListener(e->System.exit(0));

        menuPlik.add(zapisz);
        menuPlik.addSeparator();
        menuPlik.add(wyjscie);

        menuBar.add(menuPlik);
        menuBar.add(kursy);
        return menuBar;
    }
}
