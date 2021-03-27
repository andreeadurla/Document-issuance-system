package controller.controllerUser;

import view.viewUser.HomeView;
import controller.WelcomeController;
import dto.UserDto;

public class HomeController {

    private HomeView homeView;
    private UserDto userDto;

    public HomeController(UserDto userDto) {
        this.homeView = new HomeView(userDto);
        this.userDto = userDto;

        createPanels();
        addListener();
    }

    private void createPanels() {
        LocationController locationController = new LocationController(homeView, userDto);
        homeView.addPanel("Houses", locationController.getHousesPage());

        RequestController requestController = new RequestController(homeView, userDto);
        homeView.addPanel("Requests", requestController.getRequestsPage());
    }

    private void addListener() {
        homeView.addLogoutButtonListener(e -> logout());
    }


    private void logout() {
        new WelcomeController();
        homeView.dispose();
    }
}
