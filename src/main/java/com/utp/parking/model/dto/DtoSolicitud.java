package com.utp.parking.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoSolicitud {
    private Integer idSolicitud;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaRespuesta;
    private String estado;
    private Integer idUsuario;
    private Integer idVehiculo;
}
