package com.utp.parking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoRegistroRequest {
    private Integer idEstacionamiento;
    private Integer idUsuario;
    private Integer idUsuarioSeguridad;
    private String placa;
}
