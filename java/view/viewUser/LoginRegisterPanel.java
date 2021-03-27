package view.viewUser;

import javax.swing.*;
import java.awt.*;

public class LoginRegisterPanel extends JPanel {

    public LoginRegisterPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
    }

    public void addPanel(JPanel panel) {
        this.add(panel);
    }

}
