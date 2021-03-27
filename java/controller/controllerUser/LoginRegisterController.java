package controller.controllerUser;

import view.viewUser.LoginRegisterPanel;

import javax.swing.*;

public class LoginRegisterController {

    private LoginController loginController;
    private JPanel loginForm;

    private RegisterController registerController;
    private JPanel registerForm;

    private LoginRegisterPanel logRegPanel;
    private JFrame frame;

    public LoginRegisterController(JFrame frame) {
        this.frame = frame;
        loginController = new LoginController(frame);
        registerController = new RegisterController(frame);

        logRegPanel = new LoginRegisterPanel();

        addPanels();
        addListeners();
    }

    private void addPanels() {
        loginForm = loginController.getLoginForm();
        logRegPanel.addPanel(loginForm);

        registerForm = registerController.getRegisterForm();
        registerForm.setVisible(false);
        logRegPanel.addPanel(registerForm);
    }

    private void addListeners() {
        loginController.goToRegister(e -> goToRegisterForm());
        registerController.goToLogin(e -> goToLoginForm());
    }

    public void goToRegisterForm() {
        loginForm.setVisible(false);
        registerForm.setVisible(true);
    }

    public void goToLoginForm() {
        registerForm.setVisible(false);
        loginForm.setVisible(true);
    }

    public LoginRegisterPanel getLogRegPanel() {
        return logRegPanel;
    }
}
