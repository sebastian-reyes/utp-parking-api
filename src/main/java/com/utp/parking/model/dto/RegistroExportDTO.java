/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.parking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jvidal
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroExportDTO {
    
    private Integer idRegistro;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private String observacion;
    private String estacionamientoNombre;
    private String vehiculoPlaca;
    private String usuarioDni;
    private String usuarioSeguridadUsername;
    private String fechaIngresoFormatted;
    private String horaIngresoFormatted;

    public String getFechaIngresoFormatted() {
        return fechaIngreso.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public String getHoraIngresoFormatted() {
        return fechaIngreso.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
