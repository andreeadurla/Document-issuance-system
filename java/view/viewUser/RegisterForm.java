package view.viewUser;

import view.Style;
import dto.UserRegisterDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class RegisterForm extends JPanel {

    private JTextField firstnameField = new JTextField("Firstname");
    private JTextField lastnameField = new JTextField("Lastname");
    private JTextField emailField = new JTextField("Email");
    private JTextField phoneField = new JTextField("Phone");
    private JTextField cnpField = new JTextField("CNP");
    private JPasswordField passwordField = new JPasswordField("Password");
    private JPasswordField confirmPasswordField = new JPasswordField("Confirm password");
    private JButton submitButton = new JButton("Register");
    private JTextArea errorArea = new JTextArea();
    private JButton goToLoginButton = new JButton("Already have an account");

    public RegisterForm() {

        createForm();
    }

    private void createForm() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 60,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2; c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("User Register");
        Style.styleTitle(label, new Font("Arial", Font.ITALIC, 25));
        this.add(label, c);

        //Fields and Labels
        c.insets.set(5, 10, 10,10);
        c.anchor = GridBagConstraints.LINE_START;

        //Firstname
        c.gridwidth = 1; c.gridx = 0; c.gridy = 1;
        this.add(new JLabel("Firstname"), c);

        c.gridx = 0; c.gridy = 2;
        Style.styleTextField(firstnameField, 150, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(firstnameField, c);

        //Lastname
        c.gridx = 1; c.gridy = 1;
        this.add(new JLabel("Lastname"), c);

        c.gridx = 1; c.gridy = 2;
        Style.styleTextField(lastnameField, 150, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(lastnameField, c);

        //Email
        c.gridwidth = 2; c.gridx = 0; c.gridy = 3;
        this.add(new JLabel("Email"), c);

        c.gridx = 0; c.gridy = 4;
        Style.styleTextField(emailField, 320, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(emailField, c);

        //CNP
        c.gridx = 0; c.gridy = 5;
        this.add(new JLabel("CNP"), c);

        c.gridx = 0; c.gridy = 6;
        Style.styleTextField(cnpField, 320, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(cnpField, c);

        //Phone
        c.gridx = 0; c.gridy = 7;
        this.add(new JLabel("Phone"), c);

        c.gridx = 0; c.gridy = 8;
        Style.styleTextField(phoneField, 320, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(phoneField, c);

        //Password
        c.gridx = 0; c.gridy = 9;
        this.add(new JLabel("Password"), c);

        c.gridx = 0; c.gridy = 10;
        Style.styleTextField(passwordField, 320, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(passwordField, c);

        //Confirm password
        c.gridx = 0; c.gridy = 11;
        this.add(new JLabel("Confirm password"), c);

        c.gridx = 0; c.gridy = 12;
        Style.styleTextField(confirmPasswordField, 320, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(confirmPasswordField, c);

        //Error area
        c.gridx = 0; c.gridy = 13;
        Style.styleErrorArea(errorArea, 28);
        this.add(errorArea, c);

        //Go to login form button
        c.gridx = 0; c.gridy = 15;
        c.anchor = GridBagConstraints.CENTER;
        goToLoginButton.setContentAreaFilled(false);
        goToLoginButton.setOpaque(false);
        goToLoginButton.setBorderPainted(false);
        goToLoginButton.setForeground(Color.RED);
        this.add(goToLoginButton, c);

        //Submit button
        c.insets.set(30, 10, 10, 10);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0; c.gridy = 14;
        Style.styleButton(submitButton, 320, 30, new Font("Arial", Font.ITALIC, 15));
        this.add(submitButton, c);

    }

    public UserRegisterDto getRegisterInfos() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();

        String id = UUID.randomUUID().toString();
        userRegisterDto.setId(id);
        userRegisterDto.setName(firstnameField.getText() + " " + lastnameField.getText());
        userRegisterDto.setEmail(emailField.getText());
        userRegisterDto.setCnp(cnpField.getText());
        userRegisterDto.setPhone(phoneField.getText());
        userRegisterDto.setPassword(String.valueOf(passwordField.getPassword()));
        userRegisterDto.setConfirmPassword(String.valueOf(confirmPasswordField.getPassword()));

        return userRegisterDto;
    }

    public void addSubmitButtonListener(ActionListener e) {
        submitButton.addActionListener(e);
    }

    public void addGoToLoginListener(ActionListener a) {
        goToLoginButton.addActionListener(a);
    }

    public void setVisibleErrorArea(boolean flag) {
        errorArea.setVisible(flag);
    }

    public void setErrorArea(String message) {
        errorArea.setText(message);
    }

}
