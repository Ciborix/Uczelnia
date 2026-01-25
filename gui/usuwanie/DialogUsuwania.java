package gui.usuwanie;

import javax.swing.*;
import java.awt.*;

public class DialogUsuwania extends JDialog {
    private JComboBox<String> comboKryterium;
    private JTextField txtWartosc = new JTextField(15);
    private boolean zatwierdzono = false;

    public DialogUsuwania(JFrame parent, String tytul, String[] opcje) {
        super(parent, tytul, true);
        setLayout(new GridLayout(0, 1, 10, 10));

        add(new JLabel(" Wybierz kryterium usuwania:"));
        comboKryterium = new JComboBox<>(opcje);
        add(comboKryterium);

        add(new JLabel(" Wpisz szukaną wartość:"));
        add(txtWartosc);

        JButton btnUsun = new JButton("Usuń z bazy");
        btnUsun.addActionListener(e -> {
            zatwierdzono = true;
            dispose();
        });
        add(btnUsun);

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isZatwierdzono() { return zatwierdzono; }
    public int getWybranyIndeksOpcji() { return comboKryterium.getSelectedIndex() + 1; }
    public String getWartosc() { return txtWartosc.getText().trim(); }
}