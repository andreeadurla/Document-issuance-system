package service;

import dto.AdminDto;
import dto.LoginDto;
import entity.Admin;
import exception.InvalidDataException;
import map.MapAdmin;
import messages.WrongMessage;
import repository.AdminRepo;
import validators.AdminValidator;

public class AdminService {

    private final AdminRepo ar = new AdminRepo();

    public AdminDto existsAdmin(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        AdminValidator.validateEmail(email);
        AdminValidator.validatePassword(password);

        Admin admin = ar.findByEmail(email);
        if(admin == null || admin.getPassword().equals(password) == false)
            throw new InvalidDataException(WrongMessage.WRONG_EMAIL_OR_PASSWORD);

        AdminDto adminDto = MapAdmin.convertToAdminDto(admin);
        return adminDto;
    }

}

