package com.utp.parking.model.dto;

import com.utp.parking.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoUsuario {
    private Integer id_usuario;
    private String username;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String dni;
    private String carrera;
    private Boolean matriculado;
    private Role role;


}
