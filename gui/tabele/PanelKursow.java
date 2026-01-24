package gui.tabele;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelKursow extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabela;

    public PanelKursow() {
        setLayout(new BorderLayout());
        JLabel tytul = new JLabel("Lista Dostępnych Kursów", SwingConstants.CENTER);
        add(tytul, BorderLayout.NORTH);

        String[] kolumny = {"Nazwa Kursu", "Prowadzący", "Punkty ECTS"};
        tableModel = new DefaultTableModel(kolumny, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };


        tabela = new JTable(tableModel);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            private int ostatniWiersz = -1;
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                int wiersz = tabela.getSelectedRow();
                if (wiersz == ostatniWiersz && wiersz != -1) {
                    tabela.clearSelection();
                    ostatniWiersz = -1;
                } else {
                    ostatniWiersz = wiersz;
                }
            }
        });
    }

    public void ustawDane(Object[][] dane) {
        tableModel.setRowCount(0);
        for (Object[] row : dane) {
            tableModel.addRow(row);
        }
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension pref = getPreferredSize();
        return new Dimension(Integer.MAX_VALUE, pref.height);

    }
}