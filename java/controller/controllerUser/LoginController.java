package controller.controllerUser;

import view.viewUser.LoginForm;
import dto.LoginDto;
import dto.UserDto;
import service.UserService;
import validators.UserValidator;

import javax.swing.*;
import java.awt.event.ActionListener;


public class LoginController {

    private LoginForm loginForm;
    private UserService userService;

    private JFrame frame;

    public LoginController(JFrame frame) {
        this.frame = frame;
        loginForm = new LoginForm();
        userService = new UserService();

        addListeners();
    }

    private void addListeners() {
        loginForm.addSubmitButtonListener(e -> validateAccount());
    }

    private void validateAccount() {

        LoginDto loginDto = loginForm.getLoginInfos();

        try {
            UserValidator.isEmptyOrNull(loginDto.getEmail());
            UserValidator.isEmptyOrNull(loginDto.getPassword());

            UserDto userDto = userService.existsUser(loginDto);

            new HomeController(userDto);
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

    public void goToRegister(ActionListener e) {
        loginForm.addGoToRegisterListener(e);
    }
}
