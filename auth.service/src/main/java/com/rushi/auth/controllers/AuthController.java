package com.rushi.auth.controllers;


import com.rushi.auth.dtos.AuthRequestDTO;
import com.rushi.auth.dtos.JWTResponseDTO;
import com.rushi.auth.models.User;
import com.rushi.auth.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class AuthController {


    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try {
            return ResponseEntity.ok(authService.CreateUser(user));
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @PostMapping("/generateToken")
    public ResponseEntity<JWTResponseDTO> generateToken(@RequestBody AuthRequestDTO authRequestDTO){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            if(authentication.isAuthenticated()){
                return ResponseEntity.ok(authService.generateToken(authRequestDTO.getUsername()));
            }else{
                throw new RuntimeException("Invalid User Request..!");
            }
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @GetMapping("/validateToken")
    public String validateToken(@RequestParam String jwtToken){
        try {
            authService.validateToken(jwtToken);
            return "Token is valid";
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
