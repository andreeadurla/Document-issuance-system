package controller.controllerUser;

import messages.SuccessMessage;
import view.viewUser.LocationView;
import dto.HouseDto;
import dto.UserDto;
import service.HouseService;
import validators.Validator;

import javax.swing.*;
import java.util.List;

public class LocationController {

    private HouseService houseService;
    private LocationView housesPage;

    private JFrame frame;
    private UserDto userDto;

    public LocationController(JFrame frame, UserDto userDto) {
        this.frame = frame;
        this.userDto = userDto;

        houseService = new HouseService();

        housesPage = new LocationView(frame);

        addListeners();
        printHouses();
    }

    private void addListeners() {
        housesPage.addCreateAddFormButtonListener(e -> displayAddForm());
        housesPage.addSubmitButtonListener(e -> addHouse());
        housesPage.addDeleteButtonListener(e -> deleteHouse());
    }

    private void printHouses() {
        List<HouseDto> housesDto = houseService.getAllHouses(userDto.getId());

        for(HouseDto houseDto : housesDto) {
            housesPage.insertInTable(houseDto);
        }
    }

    private void displayAddForm() {
        housesPage.setEmptyTextFields();
        housesPage.createAddForm();
    }

    private void addHouse() {

        HouseDto houseDto = housesPage.getHouseInfos();
        houseDto.setUser_id(userDto.getId());

        try {
            Validator.isEmptyOrNull(houseDto.getId());
            Validator.isEmptyOrNull(houseDto.getCounty());
            Validator.isEmptyOrNull(houseDto.getCity());
            Validator.isEmptyOrNull(houseDto.getAddress());

            houseService.addNewHouse(houseDto);
            housesPage.insertInTable(houseDto);
            housesPage.closeAddForm();
        }
        catch(RuntimeException e) {
            housesPage.setErrorArea(e.getMessage());
            housesPage.setVisibleErrorArea(true);
        }

    }

    private void deleteHouse() {

        String id = housesPage.getIdOfSelectedHouse();

        try {
            Validator.isEmptyOrNull(id);

            houseService.removeHouseById(id);
            housesPage.deleteSelectedHouse();

            JOptionPane.showMessageDialog(frame, SuccessMessage.SUCCESS_DELETE);
        }
        catch(RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public LocationView getHousesPage() {
        return housesPage;
    }
}
