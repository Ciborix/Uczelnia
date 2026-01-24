package gui.dodawanie;

import javax.swing.*;
import java.awt.*;

public class DodawaniePracownikaBD extends JDialog {
    private JTextField txtImie = new JTextField(15), txtNazwisko = new JTextField(15);
    private JTextField txtPesel = new JTextField(15), txtWiek = new JTextField(5);
    private JTextField txtStaz = new JTextField(5), txtPensja = new JTextField(8);
    private JTextField txtPublikacje = new JTextField(5);

    private JComboBox<String> comboStanowisko = new JComboBox<>(new String[]{"Asystent", "Adiunkt", "Profesor Nadzwyczajny", "Profesor Zwyczajny", "Wykładowca"});
    private JComboBox<String> comboPlec = new JComboBox<>(new String[]{"M", "K"});
    private JButton btnZapisz = new JButton("Zapisz Pracownika");

    public DodawaniePracownikaBD(JFrame parent) {
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
        add(new JLabel(" Publikacje:")); add(txtPublikacje);
        add(new JLabel("")); add(btnZapisz);

        pack();
        setLocationRelativeTo(parent);
    }

    // Gettery (txtImie.getText(), txtPesel.getText(), itd.)
    public String getImie() { return txtImie.getText(); }
    public String getNazwisko() { return txtNazwisko.getText(); }
    public String getPesel() { return txtPesel.getText(); }
    public String getWiekStr() { return txtWiek.getText(); }
    public String getStazStr() { return txtStaz.getText(); }
    public String getPensjaStr() { return txtPensja.getText(); }
    public String getPublikacje() { return txtPublikacje.getText(); }
    public String getStanowisko() { return (String) comboStanowisko.getSelectedItem(); }
    public String getPlec() { return (String) comboPlec.getSelectedItem(); }
    public JButton getBtnZapisz() { return btnZapisz; }
}