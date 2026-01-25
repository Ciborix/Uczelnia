package gui.wyszukiwanie;

import javax.swing.*;
import java.awt.*;

public class DialogWyszukiwaniaKursow extends JDialog {
    private JTextField txtNazwa = new JTextField(15);
    private JTextField txtProwadzacy = new JTextField(15);
    private JTextField txtEcts = new JTextField(15);
    private boolean zatwierdzono = false;

    public DialogWyszukiwaniaKursow(JFrame parent) {
        super(parent, "Zaawansowane szukanie kursu", true);
        setLayout(new BorderLayout());

        JPanel pnlCenter = new JPanel(new GridLayout(0, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("Nazwa kursu:", SwingConstants.LEFT));
        pnlCenter.add(txtNazwa);

        pnlCenter.add(new JLabel("Prowadzący:", SwingConstants.LEFT));
        pnlCenter.add(txtProwadzacy);

        pnlCenter.add(new JLabel("Punkty ECTS:", SwingConstants.LEFT));
        pnlCenter.add(txtEcts);

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

    public String getNazwa() { return getV(txtNazwa); }
    public String getProwadzacy() { return getV(txtProwadzacy); }
    public Integer getEcts() {
        try { return Integer.parseInt(getV(txtEcts)); } catch (Exception e) { return null; }
    }
    public boolean isZatwierdzono() { return zatwierdzono; }
}