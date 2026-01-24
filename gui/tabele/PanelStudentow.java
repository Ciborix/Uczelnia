package gui.tabele;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelStudentow extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabela;

    public PanelStudentow() {
        setLayout(new BorderLayout());

        JLabel tytul = new JLabel("Lista Studentow", SwingConstants.CENTER);
        add(tytul, BorderLayout.NORTH);
        String[] kolumny = {"Imie", "Nazwisko", "Pesel", "Wiek", "Płeć", "Numer Indeks", "Rok Studiów"
                , "Erasmus", "Stopień studiów", "Stacjonarny"};

        tableModel = new DefaultTableModel(kolumny, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(tableModel);


        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.add(tabela.getTableHeader(), BorderLayout.NORTH);
        tableContainer.add(tabela, BorderLayout.CENTER);
        add(tableContainer, BorderLayout.CENTER);

        tabela.setFillsViewportHeight(false);


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

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTabela() {
        return tabela;
    }


    public void ustawDane(Object[][] dane)
    {
        tableModel.setRowCount(0);
        for(Object[] wiersz : dane)
        {
            tableModel.addRow(wiersz);
        }
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension pref = getPreferredSize();
        return new Dimension(Integer.MAX_VALUE, pref.height);
    }
}
