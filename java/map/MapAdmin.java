package map;

import dto.AdminDto;
import entity.Admin;

public class MapAdmin {

    public static AdminDto convertToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();

        adminDto.setId(admin.getId());
        adminDto.setName(admin.getName());

        return adminDto;
    }
}
