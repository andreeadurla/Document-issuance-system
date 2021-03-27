package map;

import dto.HouseDto;
import entity.House;
import entity.User;

public class MapHouse {

    public static HouseDto convertToHouseDto(House house) {
        HouseDto houseDto = new HouseDto();

        houseDto.setId(house.getId());
        houseDto.setCounty(house.getCounty());
        houseDto.setCity(house.getCity());
        houseDto.setAddress(house.getAddress());
        houseDto.setUser_id(house.getUserId());

        return houseDto;
    }

    public static House convertToHouse(HouseDto houseDto) {
        House house = new House();

        house.setId(houseDto.getId());
        house.setCounty(houseDto.getCounty());
        house.setCity(houseDto.getCity());
        house.setAddress(houseDto.getAddress());
        house.setUser(new User(houseDto.getUser_id()));

        return house;
    }
}
