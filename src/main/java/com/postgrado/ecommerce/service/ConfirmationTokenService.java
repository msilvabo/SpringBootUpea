package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken create(ConfirmationToken confirmationToken);
    ConfirmationToken getByToken(String token);
}
