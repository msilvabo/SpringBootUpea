package com.postgrado.ecommerce.mapper;

import com.postgrado.ecommerce.dto.UserDto;
import com.postgrado.ecommerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto fromEntity (User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setRole(user.getRole());
        return dto;
    }
}
