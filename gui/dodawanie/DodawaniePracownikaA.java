package gui.dodawanie;

import javax.swing.*;
import java.awt.*;

public class DodawaniePracownikaA extends JDialog {
    private JTextField txtImie = new JTextField(15), txtNazwisko = new JTextField(15);
    private JTextField txtPesel = new JTextField(15), txtWiek = new JTextField(5);
    private JTextField txtStaz = new JTextField(5), txtPensja = new JTextField(8);
    private JTextField txtNadgodziny = new JTextField(5);

    private JComboBox<String> comboStanowisko = new JComboBox<>(new String[]{
            "Referent", "Specjalista", "Starszy Specjalista", "Kierownik"
    });
    private JComboBox<String> comboPlec = new JComboBox<>(new String[]{"Meżczyzna", "Kobieta"});
    private JButton btnZapisz = new JButton("Zapisz Pracownika");

    public DodawaniePracownikaA(JFrame parent) {
        super(parent, "Nowy Pracownik Administracyjny", true);
        setLayout(new GridLayout(0, 2, 5, 5));

        add(new JLabel(" Imię:")); add(txtImie);
        add(new JLabel(" Nazwisko:")); add(txtNazwisko);
        add(new JLabel(" PESEL:")); add(txtPesel);
        add(new JLabel(" Wiek:")); add(txtWiek);
        add(new JLabel(" Płeć:")); add(comboPlec);
        add(new JLabel(" Stanowisko:")); add(comboStanowisko);
        add(new JLabel(" Staż pracy:")); add(txtStaz);
        add(new JLabel(" Pensja podst.:")); add(txtPensja);
        add(new JLabel(" Nadgodziny:")); add(txtNadgodziny);
        add(new JLabel("")); add(btnZapisz);

        pack();
        setLocationRelativeTo(parent);
    }

    public String getImie() { return txtImie.getText(); }
    public String getNazwisko() { return txtNazwisko.getText(); }
    public String getPesel() { return txtPesel.getText(); }
    public String getWiekStr() { return txtWiek.getText(); }
    public String getStazStr() { return txtStaz.getText(); }
    public String getPensjaStr() { return txtPensja.getText(); }
    public String getNadgodzinyStr() { return txtNadgodziny.getText(); }
    public String getStanowisko() { return (String) comboStanowisko.getSelectedItem(); }
    public String getPlec() { return (String) comboPlec.getSelectedItem(); }
    public JButton getBtnZapisz() { return btnZapisz; }
}