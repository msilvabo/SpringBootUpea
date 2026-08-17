package com.postgrado.ecommerce.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequestDto {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private String address;
}
