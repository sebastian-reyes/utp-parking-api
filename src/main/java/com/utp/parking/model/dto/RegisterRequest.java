package com.utp.parking.model.dto;

import com.utp.parking.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String codigo;
    private String password;
    private String nombres;
    private String apellidos;
    private String correInstitucional;
    private String dni;
    private Role rol;
    private String carrera;
    private Boolean matriculado;
}
