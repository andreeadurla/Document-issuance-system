package dto;

public class HouseDto {

    private String id;
    private String county;
    private String city;
    private String address;
    private String user_id;

    public HouseDto() {}

    public String getId() {
        return id;
    }

    public String getCounty() {
        return county;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return county + ", " + city + ", " + address;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(obj == null || !(obj instanceof HouseDto))
            return false;

        HouseDto house = (HouseDto)obj;
        boolean areEquals = this.id.equals(house.getId()) && this.county.equals(house.getCounty()) &&
                this.city.equals(house.getCity()) && this.address.equals(house.getAddress());

        return areEquals;
    }
}
