package view.viewAdmin;

import view.Style;
import dto.LoginDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel{

    private JTextField emailField = new JTextField("Email");
    private JPasswordField passwordField = new JPasswordField("Password");
    private JButton submitButton = new JButton("Login");
    private JTextArea errorArea = new JTextArea();

    public LoginForm() {
        createForm();
    }

    private void createForm() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 60,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("Admin Login");
        Style.styleTitle(label, new Font("Arial", Font.ITALIC, 25));
        this.add(label, c);

        //Fields and Labels
        c.insets.set(5, 10, 10,10);
        c.anchor = GridBagConstraints.LINE_START;

        //Email
        c.gridx = 0; c.gridy = 1;
        this.add(new JLabel("Email"), c);

        c.gridx = 0; c.gridy = 2;
        Style.styleTextField(emailField, 200, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(emailField, c);

        //Password
        c.gridx = 0; c.gridy = 3;
        this.add(new JLabel("Password"), c);

        c.gridx = 0; c.gridy = 4;
        Style.styleTextField(passwordField, 200, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(passwordField, c);

        //Error area
        c.gridx = 0; c.gridy = 5;
        Style.styleErrorArea(errorArea, 22);
        this.add(errorArea, c);

        //Submit button
        c.insets.set(30, 10, 10, 10);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0; c.gridy = 6;
        Style.styleButton(submitButton, 200, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(submitButton, c);
    }

    public LoginDto getLoginInfos() {
        LoginDto loginDto = new LoginDto();

        loginDto.setEmail(emailField.getText());
        loginDto.setPassword(String.valueOf(passwordField.getPassword()));

        return loginDto;
    }

    public void addSubmitButtonListener(ActionListener e) {
        submitButton.addActionListener(e);
    }

    public void setVisibleErrorArea(boolean flag) {
        errorArea.setVisible(flag);
    }

    public void setErrorArea(String message) {
        errorArea.setText(message);
    }
}
