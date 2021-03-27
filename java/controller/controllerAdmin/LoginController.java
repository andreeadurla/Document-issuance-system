package controller.controllerAdmin;

import view.viewAdmin.LoginForm;
import dto.AdminDto;
import dto.LoginDto;
import service.AdminService;
import validators.AdminValidator;

import javax.swing.*;

public class LoginController {

    private LoginForm loginForm;
    private AdminService adminService;

    private JFrame frame;

    public LoginController(JFrame frame) {
        this.frame = frame;
        loginForm = new LoginForm();
        adminService = new AdminService();

        addListeners();
    }

    private void addListeners() {
        loginForm.addSubmitButtonListener(e -> validateAccount());
    }

    private void validateAccount() {

        LoginDto loginDto = loginForm.getLoginInfos();

        try {
            AdminValidator.validateEmail(loginDto.getEmail());
            AdminValidator.validatePassword(loginDto.getPassword());

            AdminDto adminDto = adminService.existsAdmin(loginDto);

            new HomeController(adminDto);
            frame.dispose();
        }
        catch(RuntimeException e) {
            loginForm.setErrorArea(e.getMessage());
            loginForm.setVisibleErrorArea(true);
        }
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
