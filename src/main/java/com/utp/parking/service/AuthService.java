package com.utp.parking.service;

import com.utp.parking.model.dto.AuthResponse;
import com.utp.parking.model.dto.LoginRequest;
import com.utp.parking.model.dto.RegisterRequest;
import com.utp.parking.model.Usuario;
import com.utp.parking.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        UserDetails usuario = usuarioRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        Usuario usuario = Usuario.builder()
                .username(registerRequest.getCodigo())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .nombres(registerRequest.getNombres())
                .apellidos(registerRequest.getApellidos())
                .correoInstitucional(registerRequest.getCorreInstitucional())
                .dni(registerRequest.getDni())
                .carrera(registerRequest.getCarrera())
                .matriculado(registerRequest.getMatriculado())
                .role(registerRequest.getRol())
                .build();
        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
