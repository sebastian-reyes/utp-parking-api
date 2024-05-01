package com.utp.parking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoVehiculo {
    private Integer id_vehiculo;
    private String placa;
    private boolean aprovado;
    private String categoria;
    private boolean activo;

    private DtoUsuario usuario;
}
