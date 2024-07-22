/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.parking.model.dto;

import lombok.Data;

/**
 *
 * @author jvidal
 */
@Data
public class UsuarioExportDTO {
    
    private Integer idUsuario;
    private String username;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String dni;
    private String carrera;
    private Boolean matriculado;
    private String role;
}
