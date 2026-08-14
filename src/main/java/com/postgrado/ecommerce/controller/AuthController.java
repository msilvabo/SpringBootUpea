package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.AuthenticationRequest;
import com.postgrado.ecommerce.dto.AuthenticationResponse;
import com.postgrado.ecommerce.dto.RegistrationRequestDto;
import com.postgrado.ecommerce.service.AuthenticationService;
import com.postgrado.ecommerce.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private RegistrationService registrationService;
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequestDto dto){
        String message = registrationService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam String token){
         String message = registrationService.confirm(token);
         return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
