package com.utp.parking.service;

import com.utp.parking.interfaceService.IVehiculoService;
import com.utp.parking.model.Usuario;
import com.utp.parking.model.Vehiculo;
import com.utp.parking.model.dto.DtoSolicitud;
import com.utp.parking.model.dto.DtoUsuario;
import com.utp.parking.model.dto.DtoVehiculo;
import com.utp.parking.model.dto.VehiculoExportDTO;
import com.utp.parking.model.dto.request.DtoVehiculoRequest;
import com.utp.parking.repository.VehiculoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoService implements IVehiculoService {

    @Autowired
    private VehiculoRespository respository;
    @Autowired
    private VehiculoRespository vehiculoRespository;

    @Override
    public DtoVehiculo buscarVehiculoId(Integer id) {
        Vehiculo vehiculo = respository.findById(id).orElse(null);
        assert vehiculo != null;
        Usuario usuario = vehiculo.getUsuario();
        /*Implementar MapStruct para evitar escribir este mapeo*/
        return DtoVehiculo.builder()
                .id_vehiculo(vehiculo.getId_vehiculo())
                .placa(vehiculo.getPlaca())
                .aprovado(vehiculo.isAprobado())
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
    public void registrarVehiculo(DtoVehiculoRequest v) {
        vehiculoRespository.registrarVehiculo(v.getCategoria(), v.getPlaca(), v.getId_usuario());
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
                .aprovado(vehiculo.isAprobado())
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
        return vehiculo.isAprobado();
    }

    @Override
    public void actualizarEstaddoVehiculo(Integer id) {
        Vehiculo vehiculo = respository.findById(id).orElse(null);
        assert vehiculo != null;
        vehiculo.setAprobado(true);
        vehiculo.setActivo(true);
        vehiculoRespository.save(vehiculo);
    }

    @Override
    public List<VehiculoExportDTO> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRespository.findAll();
        return vehiculos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private VehiculoExportDTO convertToDTO(Vehiculo vehiculo) {
        VehiculoExportDTO dto = new VehiculoExportDTO();
        dto.setIdVehiculo(vehiculo.getId_vehiculo());
        dto.setPlaca(vehiculo.getPlaca());
        dto.setAprobado(vehiculo.isAprobado());
        dto.setCategoria(vehiculo.getCategoria());
        dto.setActivo(vehiculo.isActivo());
        dto.setUsername(vehiculo.getUsuario().getUsername()); // Extraemos el username del usuario propietario
        return dto;
    }
}
