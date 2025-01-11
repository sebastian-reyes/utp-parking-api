package com.utp.parking.service;

import com.utp.parking.interfaceService.ISolicitudService;
import com.utp.parking.model.Solicitud;
import com.utp.parking.model.dto.DtoSolicitud;
import com.utp.parking.model.dto.SolicitudExportDTO;
import com.utp.parking.model.dto.request.DTOSolicitudRequest;
import com.utp.parking.repository.SolicitudRepository;
import com.utp.parking.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void registrarComentario(Integer idSolicitud, String comentario) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud).orElse(null);
        solicitud.setComentario(comentario);
        solicitudRepository.save(solicitud);
    }

    @Override
    public List<SolicitudExportDTO> getAllSolicitudesForExport() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        return mapSolicitudesExportDTOs(solicitudes);
    }

    @Override
    public List<SolicitudExportDTO> getSolicitudesPorIntervaloFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Solicitud> registros = solicitudRepository.findByFechaSolicitudBetween(fechaInicio, fechaFin);
        return mapSolicitudesExportDTOs(registros);
    }

    @Override
    public List<Solicitud> findBySolicitudByUsername(String username) {
        return solicitudRepository.findByUsuarioUsername(username);
    }

    private List<SolicitudExportDTO> mapSolicitudesExportDTOs(List<Solicitud> solicitudes) {
        return solicitudes.stream().map(solicitud -> SolicitudExportDTO.builder()
                        .idSolicitud(solicitud.getId_solicitud())
                        .estado(solicitud.getEstado())
                        .fechaRespuesta(solicitud.getFechaRespuesta())
                        .fechaSolicitud(solicitud.getFechaSolicitud())
                        .usuarioUsername(solicitud.getUsuario().getUsername())
                        .vehiculoPlaca(solicitud.getVehiculo().getPlaca())
                        .build())
                .collect(Collectors.toList());
    }
}
