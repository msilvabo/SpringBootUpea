package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.RegistrationRequestDto;
import com.postgrado.ecommerce.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/register")
public class AuthController {

    RegistrationService registrationService;
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequestDto dto){
        String message = registrationService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
