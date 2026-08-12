package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto getById(UUID id);
}
