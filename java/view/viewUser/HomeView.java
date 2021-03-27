package view.viewUser;

import dto.UserDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {

    private UserDto userDto;
    private JMenuItem logoutButton = new JMenuItem("Logout");
    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);

    public HomeView(UserDto userDto) {
        this.userDto = userDto;

        initUI();

        this.pack();
        this.setTitle("User");
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

        JMenu menu = new JMenu(userDto.getName());
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
