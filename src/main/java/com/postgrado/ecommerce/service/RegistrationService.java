package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.RegistrationRequestDto;

public interface RegistrationService {
    String register(RegistrationRequestDto registrationRequestDto);
}
