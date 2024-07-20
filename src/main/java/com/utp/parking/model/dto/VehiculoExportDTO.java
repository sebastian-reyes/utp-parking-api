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
public class VehiculoExportDTO {
    
    private Integer idVehiculo;
    private String placa;
    private boolean aprobado;
    private String categoria;
    private boolean activo;
    private String username;
}
