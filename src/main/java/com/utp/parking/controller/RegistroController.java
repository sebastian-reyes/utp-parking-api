package com.utp.parking.controller;

import com.utp.parking.interfaceService.IRegistroService;
import com.utp.parking.interfaceService.ISedeService;
import com.utp.parking.interfaceService.IVehiculoService;
import com.utp.parking.model.Registro;
import com.utp.parking.model.Sede;
import com.utp.parking.model.dto.request.DtoRegistro;
import com.utp.parking.model.dto.request.DtoRegistroRequest;
import com.utp.parking.service.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registros")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RegistroController {

    @Autowired
    private IRegistroService registroService;

    @Autowired
    private IVehiculoService vehiculoService;

    @Autowired
    private ISedeService sedeService;

    @Autowired
    private EstacionamientoService estacionamientoService;

    @PostMapping("/ingreso")
    public ResponseEntity<?> registarIngreso(@RequestBody DtoRegistroRequest request) {
        Map<String, Object> response = new HashMap<>();
        request.setIdUsuario(vehiculoService.buscarVehiculo(request.getPlaca()).getUsuario().getId_usuario());
        if (vehiculoService.validarVehiculo(request.getPlaca())) {
            for(DtoRegistro r: registroService.obtenerRegistros()){
                if(r.getPlacaVehiculo().equals(request.getPlaca())){
                    response.put("mensaje", "El vehículo ya se encuentra registrado dentro del sistema");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
            registroService.registrarIngreso(request);
            Sede sede = sedeService.buscarSede(
                    estacionamientoService
                            .buscarEstacionamiento(request
                                    .getIdEstacionamiento())
                            .getSede()
                            .getId_sede());
            Integer cantidadEstacionamientos = sede.getCantidad();
            sede.setCantidad(cantidadEstacionamientos - 1);
            sedeService.actualizarCantidadEspacios(sede);
            response.put("mensaje", "El vehículo ingresó al estacionamiento");
            response.put("registro", request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("mensaje", "El vehículo no se encuentra autorizado para ingresar");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/salida/{placa}")
    public ResponseEntity<?> registrarSalida(@PathVariable String placa) {
        Map<String, Object> response = new HashMap<>();
        Integer idVehiculo = vehiculoService.buscarVehiculo(placa).getId_vehiculo();
        Registro registro = registroService.registrarSalida(idVehiculo);
        Sede sede = sedeService.buscarSede(
                estacionamientoService
                        .buscarEstacionamiento(registro.
                                getEstacionamiento()
                                .getId_estacionamiento())
                        .getSede()
                        .getId_sede());
        Integer cantidadEstacionamientos = sede.getCantidad();
        sede.setCantidad(cantidadEstacionamientos + 1);
        sedeService.actualizarCantidadEspacios(sede);
        response.put("mensaje", "El vehículo salió del estacionamiento");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/sin-salida")
    public ResponseEntity<?> listarRegistrosSinFechaDeSalida() {
        Map<String, Object> response = new HashMap<>();
        List<DtoRegistro> lstRegistros = registroService.obtenerRegistros();
        response.put("registros", lstRegistros);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
