package map;

import dto.RequestDto;
import entity.Document;
import entity.House;
import entity.Request;
import entity.User;

public class MapRequest {

    public static Request convertToRequest(RequestDto requestDto){
        Request request = new Request();

        request.setId(requestDto.getId());
        request.setDate(requestDto.getDate());
        request.setHouse(new House(requestDto.getHouseId()));
        request.setDocument(new Document(requestDto.getDocumentId()));
        request.setPurpose(requestDto.getPurpose());
        request.setUser(new User(requestDto.getUserId()));
        request.setStatus(requestDto.getStatus());

        return request;
    }

    public static RequestDto convertToRequestDto(Request request) {
        RequestDto requestDto = new RequestDto();

        requestDto.setId(request.getId());
        requestDto.setDate(request.getDate());
        requestDto.setHouse(MapHouse.convertToHouseDto(request.getHouse()));
        requestDto.setDocument(MapDocument.convertToDocumentDto(request.getDocument()));
        requestDto.setPurpose(request.getPurpose());
        requestDto.setStatus(request.getStatus());
        requestDto.setUser(MapUser.convertToUserDto(request.getUser()));

        return requestDto;
    }
    
}
