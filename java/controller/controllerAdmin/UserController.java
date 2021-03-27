package controller.controllerAdmin;

import view.viewAdmin.UserView;
import dto.AdminDto;
import dto.UserDisplayDto;
import service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;
    private UserView userView;

    private AdminDto adminDto;

    public UserController(AdminDto adminDto) {
        this.adminDto = adminDto;

        userService = new UserService();
        userView = new UserView();

        printUsers();
    }

    private void printUsers() {
        List<UserDisplayDto> users = userService.getAllUsers();

        for(UserDisplayDto user : users) {
            userView.insertInTable(user);
        }
    }

    public UserView getUserView() {
        return userView;
    }
}
