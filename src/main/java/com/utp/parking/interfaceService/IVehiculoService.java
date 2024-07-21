package com.utp.parking.interfaceService;

import com.utp.parking.model.dto.DtoVehiculo;
import com.utp.parking.model.dto.VehiculoExportDTO;
import com.utp.parking.model.dto.request.DtoVehiculoRequest;

import java.util.List;

public interface IVehiculoService {
    DtoVehiculo buscarVehiculoId(Integer id);
    void registrarVehiculo(DtoVehiculoRequest v);
    DtoVehiculo buscarVehiculo(String placa);
    Boolean validarVehiculo(String placa);
    void actualizarEstadoVehiculo(Integer id);
    List<VehiculoExportDTO> getAllVehiculos();
}
