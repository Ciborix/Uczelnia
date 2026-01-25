package gui.wyszukiwanie;

import javax.swing.*;
import java.awt.*;

public class DialogWyszukiwaniaStudenta extends JDialog {
    private JTextField txtNazwisko = new JTextField(15);
    private JTextField txtImie = new JTextField(15);
    private JTextField txtIndeks = new JTextField(15);
    private JTextField txtRok = new JTextField(15);
    private JTextField txtKurs = new JTextField(15);
    private boolean zatwierdzono = false;

    public DialogWyszukiwaniaStudenta(JFrame parent) {
        super(parent, "Zaawansowane szukanie studenta", true);

        setLayout(new BorderLayout());

        JPanel pnlCenter = new JPanel(new GridLayout(0, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("Nazwisko:", SwingConstants.LEFT)); pnlCenter.add(txtNazwisko);
        pnlCenter.add(new JLabel("Imię:", SwingConstants.LEFT));     pnlCenter.add(txtImie);
        pnlCenter.add(new JLabel("Nr Indeksu:", SwingConstants.LEFT)); pnlCenter.add(txtIndeks);
        pnlCenter.add(new JLabel("Rok studiów:", SwingConstants.LEFT)); pnlCenter.add(txtRok);
        pnlCenter.add(new JLabel("Nazwa kursu:", SwingConstants.LEFT)); pnlCenter.add(txtKurs);

        add(pnlCenter, BorderLayout.CENTER);


        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlSouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 20));

        JButton btnSzukaj = new JButton("Szukaj");
        btnSzukaj.setPreferredSize(new Dimension(100, 30));
        btnSzukaj.addActionListener(e -> {
            zatwierdzono = true;
            dispose();
        });

        pnlSouth.add(btnSzukaj);
        add(pnlSouth, BorderLayout.SOUTH);


        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }


    public String getNazwisko() { return getV(txtNazwisko); }
    public String getImie() { return getV(txtImie); }
    public String getKurs() { return getV(txtKurs); }

    public Integer getIndeks() {
        try { return Integer.parseInt(getV(txtIndeks)); } catch (Exception e) { return null; }
    }
    public Integer getRok() {
        try { return Integer.parseInt(getV(txtRok)); } catch (Exception e) { return null; }
    }

    private String getV(JTextField f) {
        String t = f.getText().trim();
        return t.isEmpty() ? null : t;
    }

    public boolean isZatwierdzono() { return zatwierdzono; }
}