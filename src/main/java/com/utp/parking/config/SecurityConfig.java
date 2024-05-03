package com.utp.parking.config;

import com.utp.parking.jwt.JwtAuthenticationFilter;
import com.utp.parking.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/registros/ingreso").hasAuthority("SEGURIDAD")
                                .requestMatchers("/registros/salida").hasAuthority("SEGURIDAD")
                                .requestMatchers("/registros/listar").hasAnyAuthority("ADMINISTRATIVO","PERSONAL_SAE")
                                /*
                                .requestMatchers("/home/alumno").hasAuthority("ALUMNO")
                                .requestMatchers("/home/docente").hasAuthority("DOCENTE")
                                .requestMatchers("/home/seguridad").hasAuthority("SEGURIDAD")
                                */
                                .anyRequest().authenticated())
                .sessionManagement(sessionManager->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
