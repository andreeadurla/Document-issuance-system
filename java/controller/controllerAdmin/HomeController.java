package controller.controllerAdmin;

import view.viewAdmin.HomeView;
import controller.WelcomeController;
import dto.AdminDto;

public class HomeController {

    private HomeView homeView;
    private AdminDto adminDto;

    public HomeController(AdminDto adminDto) {
        this.adminDto = adminDto;
        this.homeView = new HomeView(adminDto);

        createPanels();
        addListener();
    }

    private void createPanels() {
        UserController userController = new UserController(adminDto);
        homeView.addPanel("Users", userController.getUserView());

        DocumentTypeController documentTypeController = new DocumentTypeController(homeView);
        homeView.addPanel("Document Types", documentTypeController.getDocumentView());

        RequestController requestController = new RequestController(homeView);
        homeView.addPanel("Requests", requestController.getRequestView());
    }

    private void addListener() {
        homeView.addLogoutButtonListener(e -> logout());
    }

    private void logout() {
        new WelcomeController();
        homeView.dispose();
    }
}
