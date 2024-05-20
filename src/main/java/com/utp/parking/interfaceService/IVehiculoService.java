package com.utp.parking.interfaceService;

import com.utp.parking.model.Vehiculo;
import com.utp.parking.model.dto.DtoVehiculo;
import com.utp.parking.model.dto.request.DtoVehiculoRequest;

public interface IVehiculoService {
    void registrarVehiculo(DtoVehiculoRequest v);
    DtoVehiculo buscarVehiculo(String placa);
    Boolean validarVehiculo(String placa);
}
