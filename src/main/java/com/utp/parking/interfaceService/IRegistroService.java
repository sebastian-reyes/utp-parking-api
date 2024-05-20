package com.utp.parking.interfaceService;

import com.utp.parking.model.Registro;
import com.utp.parking.model.dto.request.DtoRegistroRequest;

public interface IRegistroService {
    void registrarIngreso(DtoRegistroRequest request);
    Registro registrarSalida(Integer idVehiculo);
}
