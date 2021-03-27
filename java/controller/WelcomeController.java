package controller;

import controller.controllerAdmin.LoginController;
import controller.controllerUser.LoginRegisterController;
import view.Welcome;

public class WelcomeController {

    private Welcome welcomeView;

    public WelcomeController() {
        this.welcomeView = new Welcome();

        createPanels();
    }

    private void createPanels() {
        LoginRegisterController loginRegisterController = new LoginRegisterController(welcomeView);
        welcomeView.addPanel("User", loginRegisterController.getLogRegPanel());

        LoginController loginAdminController = new LoginController(welcomeView);
        welcomeView.addPanel("Admin", loginAdminController.getLoginForm());
    }
}
