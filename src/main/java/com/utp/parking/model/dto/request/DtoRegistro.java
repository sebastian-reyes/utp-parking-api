package com.utp.parking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRegistro {
    private Integer id_registro;
    private LocalDateTime fecha_ingreso;
    private LocalDateTime fecha_salida;
    private String observacion;
    private String nombreSede;
    private String codigoUsuario;
    private String nombreUsuario;
    private Integer IdUsuario;
    private Integer IdUsuarioSeguridad;
    private String placaVehiculo;
}
