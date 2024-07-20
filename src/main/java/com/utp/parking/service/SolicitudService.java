package com.utp.parking.service;

import com.utp.parking.interfaceService.ISolicitudService;
import com.utp.parking.model.Solicitud;
import com.utp.parking.model.dto.request.DTOSolicitudRequest;
import com.utp.parking.repository.SolicitudRepository;
import com.utp.parking.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitudService implements ISolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Solicitud> listarSolicitudesId(Integer usuarioId) {
        return solicitudRepository.findByIdUsuario(usuarioId);
    }

    @Override
    public List<Solicitud> listarSolicitudes() {
        return solicitudRepository.findAll();
    }

    @Override
    public void registrarSolicitud(DTOSolicitudRequest dtoSolicitud) {
        solicitudRepository.registrarSolicitud(dtoSolicitud.getId_usuario(), dtoSolicitud.getId_vehiculo());
    }

    @Override
    public Solicitud actualizarSolicitud(Integer idSolicitud, String estado, Integer idUsuarioSae) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud).orElse(null);
        solicitud.setEstado(estado);
        solicitud.setFechaRespuesta(LocalDateTime.now());
        solicitud.setUsuarioSae(usuarioRepository.findById(idUsuarioSae).orElse(null));
        solicitudRepository.save(solicitud);
        return solicitud;
    }
}
