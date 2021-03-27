package service;

import dto.HouseDto;
import entity.House;
import exception.InvalidDataException;
import map.MapHouse;
import messages.WrongMessage;
import repository.HouseRepo;
import validators.Validator;


import java.util.List;
import java.util.stream.Collectors;

public class HouseService {

    private final HouseRepo hr = new HouseRepo();

    public void addNewHouse(HouseDto houseDto) {

        Validator.isEmptyOrNull(houseDto.getId());
        Validator.isEmptyOrNull(houseDto.getCounty());
        Validator.isEmptyOrNull(houseDto.getCity());
        Validator.isEmptyOrNull(houseDto.getAddress());
        Validator.isEmptyOrNull(houseDto.getUser_id());

        House house = MapHouse.convertToHouse(houseDto);

        house.setDeleted(false);

        boolean found = hr.findHouse(house);

        if(found == true)
            throw new InvalidDataException(WrongMessage.WRONG_ADDRESS);

        hr.insertNewHouse(house);
    }

    public List<HouseDto> getAllHouses(String userID) {
        Validator.isEmptyOrNull(userID);

        List<House> houses = hr.findAllHouses(userID);

        List<HouseDto> houseDtos = houses.stream()
                                          .map(h -> MapHouse.convertToHouseDto(h))
                                          .collect(Collectors.toList());
        return houseDtos;
    }

    public void removeHouseById(String id) {
        Validator.isEmptyOrNull(id);

        hr.deleteHouse(id);
    }
}
