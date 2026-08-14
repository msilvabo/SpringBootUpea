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

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow( ()-> new EntityNotFoundException(String.format("User with email %s Not found", email)));
    }


}
