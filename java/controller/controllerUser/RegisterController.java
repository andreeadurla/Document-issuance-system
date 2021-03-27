package controller.controllerUser;

import view.viewUser.RegisterForm;
import dto.UserRegisterDto;
import dto.UserDto;
import service.UserService;
import validators.UserValidator;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterController {

    private RegisterForm registerForm;
    private UserService userService;

    private JFrame frame;

    public RegisterController(JFrame frame) {
        this.frame = frame;
        registerForm = new RegisterForm();
        userService = new UserService();

        addListeners();
    }

    private void addListeners() {
        registerForm.addSubmitButtonListener(e -> createAccount());
    }

    public void goToLogin(ActionListener e) {
        registerForm.addGoToLoginListener(e);
    }

    public void createAccount() {

        UserRegisterDto userRegisterDto = registerForm.getRegisterInfos();

        try {
            UserValidator.validateName(userRegisterDto.getName());
            UserValidator.isEmptyOrNull(userRegisterDto.getId());
            UserValidator.validateEmail(userRegisterDto.getEmail());
            UserValidator.validateCNP(userRegisterDto.getCnp());
            UserValidator.validatePhone(userRegisterDto.getPhone());
            UserValidator.validatePassword(userRegisterDto.getPassword());
            UserValidator.validateConfirmPassword(userRegisterDto.getPassword(), userRegisterDto.getConfirmPassword());

            UserDto userDto = userService.addNewUser(userRegisterDto);

            new HomeController(userDto);
            frame.dispose();

        }
        catch(RuntimeException e) {
            registerForm.setErrorArea(e.getMessage());
            registerForm.setVisibleErrorArea(true);
        }
    }

    public RegisterForm getRegisterForm() {
        return registerForm;
    }
}
