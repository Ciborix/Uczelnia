package kontroler;

import model.Dane.KontenerDanych;
import model.comparator.Kursy.PoEctsNazwiskuProw;
import model.comparator.Osoba.PoNazwisku;
import model.comparator.Osoba.PoNazwiskuImieniu;
import model.comparator.Osoba.PoNazwiskuWieku;

import javax.swing.*;

public class Sortowanie {
    private KontenerDanych model;

    public Sortowanie(KontenerDanych model) {
        this.model = model;
    }

    public JMenu stworzMenuSortowania() {
        JMenu menuSort = new JMenu("Sortowanie");

        JMenuItem poNazwisku = new JMenuItem("Po nazwisku");
        JMenuItem poNazwiskuImieniu = new JMenuItem("Po nazwisku i imieniu");
        JMenuItem poNazwiskuWieku = new JMenuItem("Po nazwisku i wieku");
        JMenuItem poKursach = new JMenuItem("Kursy (ECTS + Prowadzący)");


        poNazwisku.addActionListener(e -> {
            model.getOsoby().sort(new PoNazwisku());
            model.powiadom("Posortowano osoby po nazwisku.");
        });

        poNazwiskuImieniu.addActionListener(e -> {
            model.getOsoby().sort(new PoNazwiskuImieniu());
            model.powiadom("Posortowano osoby po nazwisku i imieniu.");
        });

        poNazwiskuWieku.addActionListener(e -> {
            model.getOsoby().sort(new PoNazwiskuWieku());
            model.powiadom("Posortowano osoby po nazwisku i wieku.");
        });

        poKursach.addActionListener(e -> {
            model.getKursy().sort(new PoEctsNazwiskuProw());
            model.powiadom("Posortowano kursy.");
        });

        menuSort.add(poNazwisku);
        menuSort.addSeparator();
        menuSort.add(poNazwiskuImieniu);
        menuSort.addSeparator();
        menuSort.add(poNazwiskuWieku);
        menuSort.addSeparator();
        menuSort.add(poKursach);

        return menuSort;
    }
}