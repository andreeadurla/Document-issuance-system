package map;

import dto.UserDisplayDto;
import dto.UserRegisterDto;
import dto.UserDto;
import entity.User;

public class MapUser {

    public static UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());

        return userDto;
    }

    public static User convertToUser(UserRegisterDto userRegisterDto) {
        User user = new User();

        user.setId(userRegisterDto.getId());
        user.setName(userRegisterDto.getName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPhone(userRegisterDto.getPhone());
        user.setCnp(userRegisterDto.getCnp());
        user.setPassword(userRegisterDto.getPassword());

        return user;
    }


    public static UserDisplayDto convertToUserDisplayDto(User user) {
        UserDisplayDto userDto = new UserDisplayDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setCnp(user.getCnp());

        return userDto;
    }
}
