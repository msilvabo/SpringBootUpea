package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.UserDto;
import com.postgrado.ecommerce.entity.User;

import java.util.UUID;

public interface UserService {
    UserDto getById(UUID id);

    User create(User user);

    boolean existByEmail(String email);

    User getByEmail(String email);

    void enableUser(User user);
}
