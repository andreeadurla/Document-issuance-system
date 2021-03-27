package controller.controllerUser;

import messages.SuccessMessage;
import view.viewUser.RequestView;
import dto.DocumentDto;
import dto.HouseDto;
import dto.RequestDto;
import dto.UserDto;
import exception.InvalidDataException;
import messages.WrongMessage;
import service.DocumentService;
import service.HouseService;
import service.RequestService;
import validators.RequestValidator;
import validators.Validator;

import javax.swing.*;
import java.util.List;

public class RequestController {

    private RequestService requestService;
    private HouseService houseService;
    private DocumentService documentService;

    private RequestView requestsPage;

    private JFrame frame;
    private UserDto userDto;

    public RequestController(JFrame frame, UserDto userDto) {
        this.frame = frame;
        this.userDto = userDto;

        requestService = new RequestService();
        houseService = new HouseService();
        documentService = new DocumentService();

        requestsPage = new RequestView(frame);

        addListeners();
        insertRequestInTable();
    }

    private void addListeners() {
        requestsPage.addCreateAddFormButtonListener(e -> displayAddForm());
        requestsPage.addSubmitAddButtonListener(e -> addRequest());
        requestsPage.addCreateUpdateFormButtonListener(e -> displayUpdateForm());
        requestsPage.addSubmitUpdateButtonListener(e -> updateRequest());
        requestsPage.addDeleteButtonListener(e -> deleteRequest());
        requestsPage.addViewButtonListener(e -> displaySelectedRequest());
    }

    private void insertRequestInTable() {
        List<RequestDto> requestsDto = requestService.getRequestsByUserId(userDto.getId());
        requestsDto.forEach(r -> requestsPage.insertInTable(r));
    }

    private void displayAddForm() {
        requestsPage.clearComboBoxes();
        requestsPage.clearPurposeArea();

        List<HouseDto> houseDtos = houseService.getAllHouses(userDto.getId());
        houseDtos.forEach(h -> requestsPage.insertHouseInComboBox(h));

        List<DocumentDto> documentDtos = documentService.getAllDocumentTypes();
        documentDtos.forEach(d -> requestsPage.insertDocumentTypeInComboBox(d));

        requestsPage.createAddForm();
    }

    private void addRequest() {

        RequestDto requestDto = requestsPage.getRequestInfos();
        requestDto.setUser(userDto);

        try{
            RequestValidator.isEmptyOrNull(requestDto.getId());
            RequestValidator.isNull(requestDto.getDate());
            RequestValidator.isNull(requestDto.getHouse());
            RequestValidator.isNull(requestDto.getDocument());
            RequestValidator.validatePurpose(requestDto.getPurpose());
            RequestValidator.isInProcessing(requestDto.getStatus());

            requestService.addNewRequest(requestDto);
            requestsPage.insertInTable(requestDto);
            requestsPage.closeAddForm();
        }
        catch(Exception e) {
            requestsPage.setErrorArea(e.getMessage());
            requestsPage.setVisibleErrorArea(true);
        }
    }

    private void displayUpdateForm() {
        String id = requestsPage.getIdOfSelectedRequest();

        try{
            RequestValidator.isEmptyOrNull(id);

            requestsPage.clearComboBoxes();
            requestsPage.clearPurposeArea();

            List<HouseDto> houseDtos = houseService.getAllHouses(userDto.getId());
            houseDtos.forEach(h -> requestsPage.insertHouseInComboBox(h));

            List<DocumentDto> documentDtos = documentService.getAllDocumentTypes();
            documentDtos.forEach(d -> requestsPage.insertDocumentTypeInComboBox(d));

            RequestDto requestDto = requestService.getRequestById(id);

            requestsPage.setUpdateFields(requestDto);
            requestsPage.createUpdateForm();
        }
        catch(RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRequest() {
        try{
            RequestDto request = requestsPage.getUpdatedInfos();
            if(request == null)
                throw new InvalidDataException(WrongMessage.WRONG_INPUT);

            request.setUser(userDto);
            RequestValidator.isEmptyOrNull(request.getId());
            RequestValidator.isNull(request.getHouse());
            RequestValidator.isNull(request.getDocument());
            RequestValidator.validatePurpose(request.getPurpose());
            RequestValidator.validateStatus(request.getStatus());

            requestService.updateRequest(request);

            requestsPage.updateSelectedRow(request);
            requestsPage.closeUpdateForm();
        }
        catch(RuntimeException e) {
            requestsPage.setErrorArea(e.getMessage());
            requestsPage.setVisibleErrorArea(true);
        }

    }

    private void deleteRequest() {

        String id = requestsPage.getIdOfSelectedRequest();

        try {
            Validator.isEmptyOrNull(id);

            requestService.userRemoveRequestById(id);
            requestsPage.deleteSelectedRequest();

            JOptionPane.showMessageDialog(frame, SuccessMessage.SUCCESS_DELETE);
        }
        catch(RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displaySelectedRequest() {
        requestsPage.displaySelectedRow();
    }

    public RequestView getRequestsPage() {
        return requestsPage;
    }
}
