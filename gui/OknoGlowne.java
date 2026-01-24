package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OknoGlowne extends JFrame {
    public OknoGlowne() {
        setTitle("System zarządzania uczelnia PWr - Politechnika Wrocławska");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());


    }

    public void ustawMenu(JMenuBar menuBar)
    {
        setJMenuBar(menuBar);
    }
}
