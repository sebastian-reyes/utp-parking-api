package com.utp.parking.service;

import com.utp.parking.interfaceService.ISolicitudService;
import com.utp.parking.model.Solicitud;
import com.utp.parking.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService implements ISolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    public List<Solicitud> listarSolicitudesId(Integer usuarioId) {
        return solicitudRepository.findByIdUsuario(usuarioId);
    }

    @Override
    public List<Solicitud> listarSolicitudes() {
        return solicitudRepository.findAll();
    }
}
