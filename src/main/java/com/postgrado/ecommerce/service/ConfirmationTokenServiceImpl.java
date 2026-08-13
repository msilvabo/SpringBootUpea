package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.ConfirmationToken;
import com.postgrado.ecommerce.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public ConfirmationToken create(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }
}
