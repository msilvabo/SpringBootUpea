package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.UserDto;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.mapper.UserMapper;
import com.postgrado.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserDto getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("User",id)
        );
        return userMapper.fromEntity(user);
    }
}
