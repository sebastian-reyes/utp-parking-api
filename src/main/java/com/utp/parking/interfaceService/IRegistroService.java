package com.utp.parking.interfaceService;

import com.utp.parking.model.Registro;
import com.utp.parking.model.dto.RegistroExportDTO;
import com.utp.parking.model.dto.request.DtoRegistroRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface IRegistroService {
    void registrarIngreso(DtoRegistroRequest request);
    Registro registrarSalida(Integer idVehiculo);
    List<RegistroExportDTO> getAllRegistrosForExport();
    List<RegistroExportDTO> getRegistrosPorIntervaloFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
