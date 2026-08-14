package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.ConfirmationToken;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public ConfirmationToken create(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken getByToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(() -> new EntityNotFoundException("Confirmation Token not Found"));
    }

    @Override
    public void setConfirmAt(ConfirmationToken confirmationToken) {
        confirmationToken.setConfirmedAd(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);
    }
}
