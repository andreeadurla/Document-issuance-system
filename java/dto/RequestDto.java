package dto;

import java.util.Date;

public class RequestDto {

    private String id;
    private Date date;
    private HouseDto house;
    private DocumentDto document;
    private String purpose;
    private String status;
    private UserDto user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HouseDto getHouse() {
        return house;
    }

    public void setHouse(HouseDto house) {
        this.house = house;
    }

    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getHouseId() {
        return house.getId();
    }

    public String getDocumentId() {
        return document.getId();
    }

    public String getUserId() {
        return user.getId();
    }
}
