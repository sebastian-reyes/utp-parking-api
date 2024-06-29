package com.utp.parking.controller;

import com.utp.parking.interfaceService.IVehiculoService;
import com.utp.parking.model.dto.DtoVehiculo;
import com.utp.parking.model.dto.request.DtoVehiculoRequest;
import com.utp.parking.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class VehiculoController {

    @Autowired
    private IVehiculoService service;
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/placa/{placa}")
    public ResponseEntity<?> getVehiculoPlaca(@PathVariable String placa) {
        Map<String, Object> response = new HashMap<>();
        try {
            DtoVehiculo vehiculo = service.buscarVehiculo(placa);
            response.put("vehiculo", vehiculo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getVehiculoId(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            DtoVehiculo vehiculo = service.buscarVehiculoId(id);
            response.put("vehiculo", vehiculo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarVehiculo(@RequestBody DtoVehiculoRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            vehiculoService.registrarVehiculo(request);
            DtoVehiculo vehiculoRegistrado = vehiculoService.buscarVehiculo(request.getPlaca());
            response.put("mensaje", "Vehiculo registrado con exito.");
            response.put("vehiculo", vehiculoRegistrado);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/validar/{placa}")
    public ResponseEntity<?> validarVehiculo(@PathVariable String placa) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (service.validarVehiculo(placa)) {
                response.put("mensaje", "El vehiculo está autorizado");
                response.put("valido", true);
            } else {
                response.put("mensaje", "El vehiculo no está autorizado");
                response.put("valido", false);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
