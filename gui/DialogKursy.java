package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DialogKursy extends JDialog {
    private DefaultTableModel modelT;

    public DialogKursy(JFrame parent, String tytuł) {
        super(parent, tytuł, true);
        setSize(500, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        String[] kolumny = {"Nazwa kursu", "Prowadzący", "ECTS"};
        modelT = new DefaultTableModel(kolumny, 0);
        JTable tabela = new JTable(modelT);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        JButton btnZamkniji = new JButton("Zamknij");
        btnZamkniji.addActionListener(e -> dispose());
        add(btnZamkniji, BorderLayout.SOUTH);
    }

    public void zaladujTabele(Object[][] dane)
    {
        modelT.setRowCount(0);
        for(Object[] row : dane)
        {
            modelT.addRow(row);
        }
    }

}
