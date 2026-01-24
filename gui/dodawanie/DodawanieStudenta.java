package gui.dodawanie;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.osoba.student.Kurs;

public class DodawanieStudenta extends JDialog {
    private JTextField txtImie = new JTextField(15);
    private JTextField txtNazwisko = new JTextField(15);
    private JTextField txtPesel = new JTextField(15);
    private JTextField txtWiek = new JTextField(5);
    private JTextField txtIndeks = new JTextField(10);

    private JComboBox<String> comboPlec = new JComboBox<>(new String[]{"M", "K"});
    private JComboBox<Integer> comboRok = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
    private JComboBox<String> comboStopien = new JComboBox<>(new String[]{"I stopień", "II stopień"});

    private JCheckBox chkErasmus = new JCheckBox("Erasmus");
    private JCheckBox chkStacjonarny = new JCheckBox("Stacjonarny", true);

    private JList<Kurs> listaKursow;
    private JButton btnZapisz = new JButton("Zapisz Studenta");

    public DodawanieStudenta(JFrame parent, List<Kurs> dostepneKursy) {
        super(parent, "Dodaj nowego studenta", true);
        setLayout(new BorderLayout(10, 10));

        // Panel formularza
        JPanel pnlForm = new JPanel(new GridLayout(0, 2, 5, 5));
        pnlForm.add(new JLabel(" Imię:")); pnlForm.add(txtImie);
        pnlForm.add(new JLabel(" Nazwisko:")); pnlForm.add(txtNazwisko);
        pnlForm.add(new JLabel(" PESEL:")); pnlForm.add(txtPesel);
        pnlForm.add(new JLabel(" Wiek:")); pnlForm.add(txtWiek);
        pnlForm.add(new JLabel(" Nr Indeksu:")); pnlForm.add(txtIndeks);
        pnlForm.add(new JLabel(" Płeć:")); pnlForm.add(comboPlec);
        pnlForm.add(new JLabel(" Rok:")); pnlForm.add(comboRok);
        pnlForm.add(new JLabel(" Stopień:")); pnlForm.add(comboStopien);
        pnlForm.add(chkErasmus); pnlForm.add(chkStacjonarny);

        // Panel kursów
        DefaultListModel<Kurs> listModel = new DefaultListModel<>();
        for (Kurs k : dostepneKursy) listModel.addElement(k);
        listaKursow = new JList<>(listModel);
        listaKursow.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel pnlKursy = new JPanel(new BorderLayout());
        pnlKursy.setBorder(BorderFactory.createTitledBorder("Wybierz kursy (Ctrl+Klik)"));
        pnlKursy.add(new JScrollPane(listaKursow), BorderLayout.CENTER);

        add(pnlForm, BorderLayout.NORTH);
        add(pnlKursy, BorderLayout.CENTER);
        add(btnZapisz, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    // Gettery do pobierania danych
    public String getImie() { return txtImie.getText(); }
    public String getNazwisko() { return txtNazwisko.getText(); }
    public String getPesel() { return txtPesel.getText(); }
    public String getWiekStr() { return txtWiek.getText(); }
    public String getIndeksStr() { return txtIndeks.getText(); }
    public String getPlec() { return (String) comboPlec.getSelectedItem(); }
    public int getRok() { return (Integer) comboRok.getSelectedItem(); }
    public boolean isStopien1() { return comboStopien.getSelectedIndex() == 0; }
    public boolean isErasmus() { return chkErasmus.isSelected(); }
    public boolean isStacjonarny() { return chkStacjonarny.isSelected(); }
    public List<Kurs> getWybraneKursy() { return listaKursow.getSelectedValuesList(); }
    public JButton getBtnZapisz() { return btnZapisz; }
}