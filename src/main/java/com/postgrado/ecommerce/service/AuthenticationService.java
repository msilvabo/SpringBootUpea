package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.AuthenticationRequest;
import com.postgrado.ecommerce.dto.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
