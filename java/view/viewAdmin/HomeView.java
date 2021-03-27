package view.viewAdmin;

import dto.AdminDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JFrame{

    private AdminDto adminDto;
    private JMenuItem logoutButton = new JMenuItem("Logout");
    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);

    public HomeView(AdminDto adminDto) {
        this.adminDto = adminDto;

        initUI();

        this.pack();
        this.setTitle("Admin");
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void initUI() {

        createMenu();

        //Tabbed Pane
        Font font = new Font("Arial", Font.PLAIN, 18);
        tabbedPane.setFont(font);
        this.add(tabbedPane);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalGlue());

        JMenu menu = new JMenu(adminDto.getName());
        Font font = new Font("Arial", Font.ITALIC, 15);
        menu.setFont(font);
        menu.add(logoutButton);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }


    public void addPanel(String name, JPanel panel) {
        tabbedPane.addTab(name, panel);
    }

    public void addLogoutButtonListener(ActionListener e) {
        logoutButton.addActionListener(e);
    }

}
