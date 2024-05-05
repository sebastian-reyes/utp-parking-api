package com.utp.parking.controller;

import com.utp.parking.interfaceService.ISedeService;
import com.utp.parking.model.Sede;
import com.utp.parking.model.dto.DtoSede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sedes")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SedeController {

    @Autowired
    ISedeService service;

    @GetMapping
    public ResponseEntity<?> listarSedes() {
        Map<String, Object> response = new HashMap<>();
        List<DtoSede> lstSedes = new ArrayList<>();
        for (Sede s : service.listarSedes()) {
            DtoSede nuevaSede = new DtoSede(
                    s.getNombre(),
                    s.getDireccion(),
                    s.getCantidad());
            lstSedes.add(nuevaSede);
        }
        response.put("sedes", lstSedes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
