package com.rushi.auth.services;

import com.rushi.auth.dtos.JWTResponseDTO;
import com.rushi.auth.models.User;

public interface AuthService {

    String CreateUser(User user);
    JWTResponseDTO generateToken(String username);
    void validateToken(String jwtToken);

}
