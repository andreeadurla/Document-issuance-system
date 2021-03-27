package service;

import dto.LoginDto;
import dto.UserRegisterDto;
import dto.UserDisplayDto;
import dto.UserDto;
import entity.User;
import exception.InvalidDataException;
import map.MapUser;
import messages.WrongMessage;
import repository.UserRepo;
import validators.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepo ur = new UserRepo();

    public UserDto existsUser(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        UserValidator.validateEmail(email);
        UserValidator.validatePassword(password);

        User user = ur.findByEmail(email);
        if(user == null || user.getPassword().equals(password) == false)
            throw new InvalidDataException(WrongMessage.WRONG_EMAIL_OR_PASSWORD);

        UserDto userDto = MapUser.convertToUserDto(user);
        return userDto;
    }

    public UserDto addNewUser(UserRegisterDto userRegisterDto) {
        UserValidator.validateName(userRegisterDto.getName());
        UserValidator.isEmptyOrNull(userRegisterDto.getId());
        UserValidator.validateEmail(userRegisterDto.getEmail());
        UserValidator.validateCNP(userRegisterDto.getCnp());
        UserValidator.validatePhone(userRegisterDto.getPhone());
        UserValidator.validatePassword(userRegisterDto.getPassword());
        UserValidator.validateConfirmPassword(userRegisterDto.getPassword(), userRegisterDto.getConfirmPassword());

        User userDB = ur.findByEmailOrCNP(userRegisterDto.getEmail(), userRegisterDto.getCnp());

        if(userDB != null) {

            if(userDB.getEmail().equals(userRegisterDto.getEmail()))
                throw new InvalidDataException(WrongMessage.WRONG_EMAIL);

            throw new InvalidDataException(WrongMessage.WRONG_CNP);
        }

        User user = MapUser.convertToUser(userRegisterDto);

        ur.insertNewUser(user);

        UserDto userDto = MapUser.convertToUserDto(user);
        return userDto;
    }

    public List<UserDisplayDto> getAllUsers() {
        List<User> users = ur.findAllUsers();

        List<UserDisplayDto> userDtos = users.stream()
                                             .map(u -> MapUser.convertToUserDisplayDto(u))
                                             .collect(Collectors.toList());
        return userDtos;
    }

}
