package com.utp.parking.interfaceService;

import com.utp.parking.model.Solicitud;

import java.util.List;
import java.util.Optional;

public interface ISolicitudService {
    List<Solicitud> listarSolicitudesId(Integer usuarioId);
    List<Solicitud> listarSolicitudes();
}
