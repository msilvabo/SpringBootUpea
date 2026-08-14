package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.RegistrationRequestDto;
import com.postgrado.ecommerce.entity.ConfirmationToken;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.exception.EmailAlreadyUsed;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService{

    private UserService userService;
    private RoleService roleService;
    private ConfirmationTokenService confirmationTokenService;
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegistrationRequestDto dto) {

        // validate if email already used
        boolean exitUser = userService.existByEmail(dto.getEmail());
        if (exitUser){
            throw new EmailAlreadyUsed(dto.getEmail());
        }

        //create user
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        //TODO: Encrypt Password
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());

        String endedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(endedPassword);

        user.setRole(roleService.getByName("user"));

        userService.create(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5),user);
        confirmationTokenService.create(confirmationToken);
        //TODO: Send email with confirmation token

        return token;
    }

    @Override
    public String confirm(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getByToken(token);
        if (confirmationToken.getConfirmedAd()!=null){
            throw new RuntimeException("Token is already confirmed");
        }
        System.out.println("--- VERIFICACIÓN DE EXPIRACIÓN DE TOKEN ---");
        System.out.println("Fecha de expiración del token: " + confirmationToken.getExpiresAt());
        System.out.println("Fecha y hora actual del sistema: " + LocalDateTime.now());
        boolean isExpired = confirmationToken.getExpiresAt().isBefore(LocalDateTime.now());
        System.out.println("¿Está expirado? (isBefore): " + isExpired);

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token expired");
        }
        userService.enableUser(confirmationToken.getUser());
        confirmationTokenService.setConfirmAt(confirmationToken);
        return "User account has been enabled sucess";
    }
}
