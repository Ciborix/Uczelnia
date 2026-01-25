package gui.dodawanie;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.osoba.Osoba;
import model.osoba.student.Student;
import model.osoba.student.Kurs;

public class DialogPrzypisaniaKursu extends JDialog {
    private JComboBox<String> comboStudenci;
    private JComboBox<Kurs> comboKursy;
    private boolean zatwierdzono = false;

    public DialogPrzypisaniaKursu(JFrame parent, List<Osoba> osoby, List<Kurs> kursy) {
        super(parent, "Przypisz Kurs do Studenta", true);
        setLayout(new GridLayout(0, 1, 10, 10));


        add(new JLabel(" Wybierz Studenta (Indeks):"));
        comboStudenci = new JComboBox<>();
        for (Osoba o : osoby) {
            if (o instanceof Student s) {
                comboStudenci.addItem(s.getNrIndeks() + " - " + s.getNazwisko());
            }
        }
        add(comboStudenci);


        add(new JLabel(" Wybierz Kurs z bazy:"));
        comboKursy = new JComboBox<>();
        for (Kurs k : kursy) {
            comboKursy.addItem(k);
        }
        add(comboKursy);


        JPanel pnlPrzyciski = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAnuluj = new JButton("Anuluj");
        btnAnuluj.addActionListener(e -> dispose());

        JButton btnOk = new JButton("Przypisz");
        btnOk.addActionListener(e -> {
            if (comboStudenci.getSelectedItem() != null && comboKursy.getSelectedItem() != null) {
                zatwierdzono = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Wybierz studenta i kurs!");
            }
        });

        pnlPrzyciski.add(btnAnuluj);
        pnlPrzyciski.add(btnOk);
        add(pnlPrzyciski);

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isZatwierdzono() {
        return zatwierdzono;
    }

    public String getWybranyIndeks() {
        String selected = (String) comboStudenci.getSelectedItem();
        return selected != null ? selected.split(" - ")[0] : "";
    }

    public Kurs getWybranyKurs() {
        return (Kurs) comboKursy.getSelectedItem();
    }
}