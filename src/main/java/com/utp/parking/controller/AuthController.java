package com.utp.parking.controller;

import com.utp.parking.model.dto.AuthResponse;
import com.utp.parking.model.dto.LoginRequest;
import com.utp.parking.model.dto.RegisterRequest;
import com.utp.parking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/"})
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
