package com.utp.parking.controller;

import com.utp.parking.interfaceService.IEstacionamientoService;
import com.utp.parking.model.Estacionamiento;
import com.utp.parking.model.dto.DtoEstacionamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estacionamientos")
public class EstacionamientoController {

    @Autowired
    IEstacionamientoService service;

    @GetMapping("/{id_sede}/{piso}")
    private ResponseEntity<?> listarEstacionamientosDisponiblesPorSede(@PathVariable Integer id_sede,
                                                                       @PathVariable Integer piso) {
        Map<String, Object> response = new HashMap<>();
        List<DtoEstacionamiento> lstEstacionamientos = new ArrayList<>();
        for (Estacionamiento e : service.listarEstacionamientos(id_sede, piso)) {
            DtoEstacionamiento estacionamiento = new DtoEstacionamiento(
                    e.getId_estacionamiento(),
                    e.getPiso(),
                    e.getNumero(),
                    e.getDisponible(),
                    e.getNombre());
            lstEstacionamientos.add(estacionamiento);
        }
        response.put("estacionamientos", lstEstacionamientos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
