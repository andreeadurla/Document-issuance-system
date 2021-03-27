package service;

import dto.RequestDto;
import entity.*;
import exception.InvalidDataException;
import map.MapRequest;
import messages.WrongMessage;
import repository.RequestRepo;
import validators.RequestValidator;

import java.util.List;
import java.util.stream.Collectors;

public class RequestService {

    private final RequestRepo rr = new RequestRepo();

    public void addNewRequest(RequestDto requestDto) {
        RequestValidator.isEmptyOrNull(requestDto.getId());
        RequestValidator.isNull(requestDto.getDate());
        RequestValidator.isNull(requestDto.getHouse());
        RequestValidator.isNull(requestDto.getDocument());
        RequestValidator.validatePurpose(requestDto.getPurpose());
        RequestValidator.isNull(requestDto.getUser());
        RequestValidator.isInProcessing(requestDto.getStatus());

        Request request = MapRequest.convertToRequest(requestDto);

        request.setDeleted(false);

        long noRequests = rr.getNoRequests(request);

        if(noRequests >= 3)
            throw new InvalidDataException(WrongMessage.WRONG_NO_REQUESTS);

        rr.insertNewRequest(request);
    }

    public void userRemoveRequestById(String id) {
        RequestValidator.isEmptyOrNull(id);

        Request request = rr.findById(id);
        if(request != null) {
            String status = request.getStatus();

            if(status.equals(Status_level.IN_PROCESSING.toString()) == false)
                throw new InvalidDataException(WrongMessage.WRONG_USER_DELETE_REQUEST);

            rr.deleteByUpdatingColumn(id);
        }
    }

    public void adminRemoveRequestById(String id) {
        RequestValidator.isEmptyOrNull(id);

        Request request = rr.findById(id);
        if(request != null) {
            String status = request.getStatus();

            if(status.equals(Status_level.IN_PROCESSING.toString()) == true)
                throw new InvalidDataException(WrongMessage.WRONG_ADMIN_DELETE_REQUEST);

            rr.deleteByUpdatingColumn(id);
        }
    }

    public List<RequestDto> getRequestsByUserId(String userID) {
        RequestValidator.isEmptyOrNull(userID);

        List<Request> requests = rr.findByUserID(userID);

        List<RequestDto> requestDtos = requests.stream()
                                                .map(r -> MapRequest.convertToRequestDto(r))
                                                .collect(Collectors.toList());
        return requestDtos;
    }

    public List<RequestDto> getAllRequests() {

        List<Request> requests = rr.findAll();


        List<RequestDto> requestDtos = requests.stream()
                                                .map(r -> MapRequest.convertToRequestDto(r))
                                                .collect(Collectors.toList());
        return requestDtos;
    }

    public RequestDto getRequestById(String id) {
        RequestValidator.isEmptyOrNull(id);

        Request request = rr.findById(id);

        if(request == null)
            throw new InvalidDataException(WrongMessage.WRONG_ID);

        RequestDto requestDto = MapRequest.convertToRequestDto(request);

        return requestDto;
    }

    public List<RequestDto> getRequestsByStatus(String status) {

        RequestValidator.validateStatus(status);

        List<Request> requests = rr.findByStatus(status);

        List<RequestDto> requestDtos = requests.stream()
                                                .map(r -> MapRequest.convertToRequestDto(r))
                                                .collect(Collectors.toList());

        return requestDtos;
    }

    public void updateRequest(RequestDto requestDto) {
        RequestValidator.isEmptyOrNull(requestDto.getId());
        RequestValidator.isNull(requestDto.getHouse());
        RequestValidator.isNull(requestDto.getDocument());
        RequestValidator.validatePurpose(requestDto.getPurpose());

        String status = requestDto.getStatus();
        RequestValidator.validateStatus(status);

        if(status.equals(Status_level.IN_PROCESSING.toString()) == false)
            throw new InvalidDataException(WrongMessage.WRONG_UPDATE_REQUEST);

        Request request = MapRequest.convertToRequest(requestDto);
        long noRequests = rr.getNoRequests(request);

        if(noRequests >= 3)
            throw new InvalidDataException(WrongMessage.WRONG_NO_REQUESTS);

        rr.updateRequest(request);
    }

    public void updateRequestStatus(String id, String status) {

        RequestValidator.isEmptyOrNull(id);
        RequestValidator.validateStatus(status);

        Request request = rr.findById(id);
        String currentStatus = request.getStatus();

        System.out.println(currentStatus);
        if(currentStatus.equals(Status_level.IN_PROCESSING.toString()) == false)
            throw new InvalidDataException(WrongMessage.WRONG_UPDATE_STATUS);

        rr.updateRequestStatus(id, status);

        request.setStatus(status);
    }

}
