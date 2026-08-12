package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.UserDto;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }
}
