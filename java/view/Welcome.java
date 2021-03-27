package view;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JFrame{

    private JTabbedPane tabbedPane = new JTabbedPane();

    public Welcome() {
        initUI();
    }

    private void initUI() {

        //Tabbed Pane
        Font font = new Font("Arial", Font.PLAIN, 18);
        tabbedPane.setFont(font);
        this.add(tabbedPane);

        this.pack();
        this.setTitle("Welcome");
        this.setSize(800, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void addPanel(String name, JPanel panel) {
        tabbedPane.addTab(name, panel);
    }

}
