package com.utp.parking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoSede {
    private String nombre;
    private String direccion;
    private Integer cantidad;
}
