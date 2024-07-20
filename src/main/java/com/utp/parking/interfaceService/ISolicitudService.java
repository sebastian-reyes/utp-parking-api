package com.utp.parking.interfaceService;

import com.utp.parking.model.Solicitud;
import com.utp.parking.model.dto.DtoSolicitud;
import com.utp.parking.model.dto.SolicitudExportDTO;
import com.utp.parking.model.dto.request.DTOSolicitudRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ISolicitudService {
    List<Solicitud> listarSolicitudesId(Integer usuarioId);
    List<Solicitud> listarSolicitudes();
    void registrarSolicitud(DTOSolicitudRequest dtoSolicitud);
    Solicitud actualizarSolicitud(Integer idSolicitud, String estado, Integer idUsuarioSae);
    List<SolicitudExportDTO> getAllSolicitudesForExport();
    List<SolicitudExportDTO> getSolicitudesPorIntervaloFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Solicitud> findBySolicitudByUsername(String username);
}
