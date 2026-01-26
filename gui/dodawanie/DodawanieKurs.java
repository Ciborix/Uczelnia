package gui.dodawanie;

import javax.swing.*;
import java.awt.*;

public class DodawanieKurs extends JDialog {
    private JTextField txtNazwa, txtProwadzacy, txtEcts;
    private boolean zatwierdzono = false;

    public DodawanieKurs(JFrame parent) {
        super(parent, "Nowy Kurs", true);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2, 5, 5));
        setLocationRelativeTo(parent);

        add(new JLabel(" Nazwa:"));
        txtNazwa = new JTextField();
        add(txtNazwa);

        add(new JLabel(" Prowadzący:"));
        txtProwadzacy = new JTextField();
        add(txtProwadzacy);

        add(new JLabel(" Punkty ECTS:"));
        txtEcts = new JTextField();
        add(txtEcts);

        JButton btnAnuluj = new JButton("Anuluj");
        btnAnuluj.addActionListener(e -> dispose());
        add(btnAnuluj);

        JButton btnOk = new JButton("Dodaj");
        btnOk.addActionListener(e -> {
            zatwierdzono = true;
            dispose();
        });
        add(btnOk);
        this.pack();
        this.setLocationRelativeTo(parent);
    }

    public boolean isZatwierdzono() { return zatwierdzono; }
    public String getNazwa() { return txtNazwa.getText(); }
    public String getProwadzacy() { return txtProwadzacy.getText(); }
    public String getEctsRaw() { return txtEcts.getText(); }
}