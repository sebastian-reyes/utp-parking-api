package com.utp.parking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOSolicitudRequest {
    private Integer id_usuario;
    private Integer id_vehiculo;
}
