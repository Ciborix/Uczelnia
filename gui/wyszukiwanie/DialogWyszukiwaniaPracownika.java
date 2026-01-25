package gui.wyszukiwanie;

import javax.swing.*;
import java.awt.*;

public class DialogWyszukiwaniaPracownika extends JDialog {
    private JTextField txtNazwisko = new JTextField(15);
    private JTextField txtImie = new JTextField(15);
    private JTextField txtStanowisko = new JTextField(15);
    private JTextField txtStaz = new JTextField(15);
    private JTextField txtNadgodziny = new JTextField(15);
    private JTextField txtPensja = new JTextField(15);
    private boolean zatwierdzono = false;

    public DialogWyszukiwaniaPracownika(JFrame parent) {
        super(parent, "Zaawansowane szukanie pracownika", true);
        setLayout(new BorderLayout());


        JPanel pnlCenter = new JPanel(new GridLayout(0, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("Nazwisko:", SwingConstants.LEFT)); pnlCenter.add(txtNazwisko);
        pnlCenter.add(new JLabel("Imię:", SwingConstants.LEFT));     pnlCenter.add(txtImie);
        pnlCenter.add(new JLabel("Stanowisko:", SwingConstants.LEFT)); pnlCenter.add(txtStanowisko);
        pnlCenter.add(new JLabel("Staż pracy:", SwingConstants.LEFT)); pnlCenter.add(txtStaz);
        pnlCenter.add(new JLabel("Nadgodziny:", SwingConstants.LEFT)); pnlCenter.add(txtNadgodziny);
        pnlCenter.add(new JLabel("Pensja:", SwingConstants.LEFT));     pnlCenter.add(txtPensja);

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

    private String getV(JTextField f) {
        String t = f.getText().trim();
        return t.isEmpty() ? null : t;
    }

    public String getNazwisko() { return getV(txtNazwisko); }
    public String getImie() { return getV(txtImie); }
    public String getStanowisko() { return getV(txtStanowisko); }
    public Double getStaz() { try { return Double.parseDouble(getV(txtStaz).replace(',', '.')); } catch (Exception e) { return null; } }
    public Integer getNadgodziny() { try { return Integer.parseInt(getV(txtNadgodziny)); } catch (Exception e) { return null; } }
    public Double getPensja() { try { return Double.parseDouble(getV(txtPensja).replace(',', '.')); } catch (Exception e) { return null; } }
    public boolean isZatwierdzono() { return zatwierdzono; }
}