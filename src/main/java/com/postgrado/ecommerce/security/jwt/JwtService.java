package com.postgrado.ecommerce.security.jwt;

import com.postgrado.ecommerce.entity.User;

public interface JwtService {
    String createToken(User user);

    User decodeToken(String token);
}
