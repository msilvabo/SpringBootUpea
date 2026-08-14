package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.AuthenticationRequest;
import com.postgrado.ecommerce.dto.AuthenticationResponse;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authResult = authenticationManager.authenticate(auth);
        User user = (User) authResult.getPrincipal();
        String accessToken = jwtService.createToken(user);
        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .build();
    }
}
