package com.utp.parking.controller;

import com.utp.parking.interfaceService.ISolicitudService;
import com.utp.parking.interfaceService.IVehiculoService;
import com.utp.parking.model.Solicitud;
import com.utp.parking.model.dto.DtoSolicitud;
import com.utp.parking.model.dto.request.DTOComentarioRequest;
import com.utp.parking.model.dto.request.DTOSolicitudPathRequest;
import com.utp.parking.model.dto.request.DTOSolicitudRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SolicitudController {

    @Autowired
    private ISolicitudService solicitudService;

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping()
    public ResponseEntity<?> listarSolicitudes() {
        List<DtoSolicitud> lstSolicitudes = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        for (Solicitud s : solicitudService.listarSolicitudes()) {
            Integer idUsuarioSae = s.getUsuarioSae() != null ? s.getUsuarioSae().getId_usuario() : null;
            if (s.getComentario() == null || s.getComentario().isEmpty()) {
                s.setComentario("No hay comentarios para esta Solicitud, por favor espere a que un agente del SAE responda su solicitud.");
            }
            DtoSolicitud nuevaSolicitud = new DtoSolicitud(
                    s.getId_solicitud(),
                    s.getFechaSolicitud(),
                    s.getFechaRespuesta(),
                    s.getEstado(),
                    s.getComentario(),
                    s.getUsuario().getId_usuario(),
                    s.getVehiculo().getId_vehiculo(),
                    idUsuarioSae
            );
            lstSolicitudes.add(nuevaSolicitud);
        }
        response.put("solicitudes", lstSolicitudes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarSolicitudesId(@PathVariable int id) {
        List<DtoSolicitud> lstSolicitudes = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        for (Solicitud s : solicitudService.listarSolicitudesId(id)) {
            Integer idUsuarioSae = s.getUsuarioSae() != null ? s.getUsuarioSae().getId_usuario() : null;
            if (s.getComentario() == null || s.getComentario().isEmpty()) {
                s.setComentario("No hay comentarios para esta Solicitud, por favor espere a que un agente del SAE responda su solicitud.");
            }
            DtoSolicitud nuevaSolicitud = new DtoSolicitud(
                    s.getId_solicitud(),
                    s.getFechaSolicitud(),
                    s.getFechaRespuesta(),
                    s.getEstado(),
                    s.getComentario(),
                    s.getUsuario().getId_usuario(),
                    s.getVehiculo().getId_vehiculo(),
                    idUsuarioSae
            );
            lstSolicitudes.add(nuevaSolicitud);
        }
        response.put("solicitudes", lstSolicitudes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registarSolicitud(@RequestBody DTOSolicitudRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            solicitudService.registrarSolicitud(request);
            response.put("mensaje", "Solicitud registrada con exito.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/respuesta/{id}")
    public ResponseEntity<?> respuestaSolicitud(@PathVariable int id, @RequestBody DTOSolicitudPathRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Solicitud solicitud = solicitudService.actualizarSolicitud(id, request.getEstado(), request.getIdSae());
            if (request.getEstado().equals("Aceptado")) {
                vehiculoService.actualizarEstaddoVehiculo(solicitud.getVehiculo().getId_vehiculo());
            }
            response.put("mensaje", "Estado de la solicitud: " + solicitud.getEstado());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/respuesta/comentario/{id}")
    public ResponseEntity<?> registrarComentario(@PathVariable int id, @RequestBody DTOComentarioRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            solicitudService.registrarComentario(id, request.getComentario());
            response.put("comentario", request.getComentario());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
