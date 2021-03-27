package controller.controllerAdmin;

import messages.SuccessMessage;
import view.viewAdmin.RequestView;
import dto.RequestDto;
import entity.Status_level;
import service.RequestService;
import validators.RequestValidator;
import validators.Validator;

import javax.swing.*;
import java.util.List;

public class RequestController {

    private RequestService requestService;
    private RequestView requestView;
    private JFrame frame;

    public RequestController(JFrame frame) {
        this.frame = frame;

        requestService = new RequestService();
        requestView = new RequestView(frame);

        addListeners();
        printRequests();
        requestView.insertStatusInComboBox();
    }

    private void addListeners() {
        requestView.addAllButtonListener(e -> viewAllRequests());
        requestView.addInProcessingButtonListener(e -> viewInProcessingRequests());
        requestView.addAcceptedButtonListener(e -> viewAcceptedRequests());
        requestView.addRejectedButtonListener(e -> viewRejectedRequests());

        requestView.addViewButtonListener(e -> displaySelectedRequest());
        requestView.addUpdateButtonListener(e -> updateSelectedRequest());
        requestView.addSubmitButtonListener(e -> updateStatusRequest());
        requestView.addDeleteButtonListener(e -> deleteSelectedRequest());
    }

    private void viewAllRequests() {
        requestView.clearAllRequestsTable();

        List<RequestDto> requestsDto = requestService.getAllRequests();
        requestsDto.forEach(r -> requestView.insertInAllRequestsTable(r));

        requestView.viewAllRequestsTable();
    }

    private void viewInProcessingRequests() {
        requestView.clearInProcessingRequestTable();

        List<RequestDto> requestsDto = requestService.getRequestsByStatus(Status_level.IN_PROCESSING.toString());
        requestsDto.forEach(r -> requestView.insertInProcessingRequestsTable(r));

        requestView.viewInProcessingRequestsTable();
    }

    private void viewAcceptedRequests() {
        requestView.clearAcceptedRequestTable();

        List<RequestDto> requestsDto = requestService.getRequestsByStatus(Status_level.ACCEPTED.toString());
        requestsDto.forEach(r -> requestView.insertInAcceptedRequestsTable(r));

        requestView.viewAcceptedRequestsTable();
    }

    private void viewRejectedRequests() {
        requestView.clearRejectedRequestTable();

        List<RequestDto> requestsDto = requestService.getRequestsByStatus(Status_level.REJECTED.toString());
        requestsDto.forEach(r -> requestView.insertInRejectedRequestsTable(r));

        requestView.viewRejectedRequestsTable();
    }

    private void printRequests() {
        List<RequestDto> requestsDto = requestService.getAllRequests();

        requestsDto.forEach(r -> requestView.insertInAllRequestsTable(r));

        requestsDto.stream()
                .filter(r -> r.getStatus().equals(Status_level.ACCEPTED.toString()))
                .forEach(r -> requestView.insertInAcceptedRequestsTable(r));

        requestsDto.stream()
                    .filter(r -> r.getStatus().equals(Status_level.REJECTED.toString()))
                    .forEach(r -> requestView.insertInRejectedRequestsTable(r));
    }

    private void displaySelectedRequest() {
        requestView.displaySelectedRow();
        requestView.deselectTableRows();
    }

    private void updateSelectedRequest() {

        String id = requestView.getIdOfSelectedRequest();

        try {
            Validator.isEmptyOrNull(id);

            RequestDto requestDto = requestService.getRequestById(id);
            requestView.setUpdateFields(requestDto);

            requestView.createUpdateForm();
        }
        catch(RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStatusRequest() {
        String id = requestView.getIdOfSelectedRequest();
        String status = requestView.getSelectedStatus();

        try {
            Validator.isEmptyOrNull(id);
            RequestValidator.validateStatus(status);

            requestService.updateRequestStatus(id, status);

            requestView.updateStatusOfSelectedRequest(status);

            requestView.closeUpdateForm();
            requestView.deselectTableRows();
        }
        catch(RuntimeException e) {
            requestView.setErrorArea(e.getMessage());
            requestView.setVisibleErrorArea(true);
        }
        finally {
            requestView.deselectTableRows();
        }
    }

    private void deleteSelectedRequest() {
        String id = requestView.getIdOfSelectedRequest();

        try {
            Validator.isEmptyOrNull(id);

            requestService.adminRemoveRequestById(id);

            requestView.deleteSelectedRow();
            JOptionPane.showMessageDialog(null, SuccessMessage.SUCCESS_DELETE);
        }
        catch(RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            requestView.deselectTableRows();
        }
    }

    public RequestView getRequestView() {
        return requestView;
    }
}

