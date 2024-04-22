package com.utp.parking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoEstacionamiento {

    private Integer id_estacionamiento;
    private Integer piso;
    private Integer numero;
    private Boolean disponible;
    private String nombre;
}
