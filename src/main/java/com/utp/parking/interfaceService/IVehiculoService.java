package com.utp.parking.interfaceService;

import com.utp.parking.model.Vehiculo;
import com.utp.parking.model.dto.DtoVehiculo;

public interface IVehiculoService {
    public Vehiculo registrarVehiculo(Vehiculo v);
    public DtoVehiculo buscarVehiculo(String placa);
    public Boolean validarVehiculo(String placa);
}
