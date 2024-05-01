package com.utp.parking.interfaceService;

import com.utp.parking.model.Vehiculo;
import com.utp.parking.model.dto.DtoVehiculo;

public interface IVehiculoService {
    Vehiculo registrarVehiculo(Vehiculo v);
    DtoVehiculo buscarVehiculo(String placa);
    Boolean validarVehiculo(String placa);
}
