package com.utp.parking.service;

import com.utp.parking.interfaceService.IVehiculoService;
import com.utp.parking.model.Usuario;
import com.utp.parking.model.Vehiculo;
import com.utp.parking.model.dto.DtoUsuario;
import com.utp.parking.model.dto.DtoVehiculo;
import com.utp.parking.repository.VehiculoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService implements IVehiculoService {

    @Autowired
    private VehiculoRespository respository;

    @Override
    public Vehiculo registrarVehiculo(Vehiculo v) {
        return respository.save(v);
    }

    @Override
    public DtoVehiculo buscarVehiculo(String placa) {
        Vehiculo vehiculo = respository.findByPlaca(placa);
        assert vehiculo != null;
        Usuario usuario = vehiculo.getUsuario();
        /*Implementar MapStruct para evitar escribir este mapeo*/
        return DtoVehiculo.builder()
                .id_vehiculo(vehiculo.getId_vehiculo())
                .placa(vehiculo.getPlaca())
                .aprovado(vehiculo.isAprovado())
                .categoria(vehiculo.getCategoria())
                .activo(vehiculo.isActivo())
                .usuario(DtoUsuario.builder()
                        .id_usuario(usuario.getId_usuario())
                        .username(usuario.getUsername())
                        .nombres(usuario.getNombres())
                        .apellidos(usuario.getApellidos())
                        .correoInstitucional(usuario.getCorreoInstitucional())
                        .dni(usuario.getDni())
                        .carrera((usuario.getCarrera() == null) ? "-" : usuario.getCarrera())
                        .matriculado(usuario.getMatriculado())
                        .role(usuario.getRole())
                        .build())
                .build();
    }

    @Override
    public Boolean validarVehiculo(String placa) {
        Vehiculo vehiculo = respository.findByPlaca(placa);
        assert vehiculo != null;
        return vehiculo.isAprovado();
    }
}
