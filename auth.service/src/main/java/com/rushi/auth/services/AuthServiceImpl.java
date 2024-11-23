package com.rushi.auth.services;

import com.rushi.auth.dtos.JWTResponseDTO;
import com.rushi.auth.models.User;
import com.rushi.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTService jwtService;
    private final UserDetailsServiceImpl userDetailsService;



    @Override
    public String CreateUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User has been created successfully";
    }

    @Override
    public JWTResponseDTO generateToken(String username) {
        User user = userRepository.findByUsername(username);
        return JWTResponseDTO.builder().accessToken(jwtService.generateToken(user)).build();

    }

    @Override
    public void validateToken(String jwtToken) {

        String username = jwtService.extractUserName(jwtToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        jwtService.validateToken(jwtToken, userDetails);

    }
}
