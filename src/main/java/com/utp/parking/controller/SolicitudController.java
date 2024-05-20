package com.utp.parking.controller;

import com.utp.parking.interfaceService.ISolicitudService;
import com.utp.parking.interfaceService.IVehiculoService;
import com.utp.parking.model.Solicitud;
import com.utp.parking.model.Vehiculo;
import com.utp.parking.model.dto.DtoSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
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
            DtoSolicitud nuevaSolicitud = new DtoSolicitud(
                    s.getId_solicitud(),
                    s.getFechaSolicitud(),
                    s.getFechaRespuesta(),
                    s.getEstado(),
                    s.getUsuario().getId_usuario(),
                    s.getVehiculo().getId_vehiculo()
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
            DtoSolicitud nuevaSolicitud = new DtoSolicitud(
                    s.getId_solicitud(),
                    s.getFechaSolicitud(),
                    s.getFechaRespuesta(),
                    s.getEstado(),
                    s.getUsuario().getId_usuario(),
                    s.getVehiculo().getId_vehiculo()
            );
            lstSolicitudes.add(nuevaSolicitud);
        }
        response.put("solicitudes", lstSolicitudes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registarSolicitud(@RequestBody Vehiculo vehiculo) {
        Map<String, Object> response = new HashMap<>();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
