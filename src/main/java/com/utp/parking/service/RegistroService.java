package com.utp.parking.service;

import com.utp.parking.interfaceService.IRegistroService;
import com.utp.parking.model.Registro;
import com.utp.parking.model.dto.RegistroExportDTO;
import com.utp.parking.model.dto.request.DtoRegistro;
import com.utp.parking.model.dto.request.DtoRegistroRequest;
import com.utp.parking.repository.RegistroRepository;
import com.utp.parking.repository.VehiculoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroService implements IRegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    VehiculoRespository vehiculoRespository;

    @Override
    public void registrarIngreso(DtoRegistroRequest request) {
        registroRepository.insertarRegistro(request.getIdEstacionamiento(),
                request.getIdUsuario(),
                request.getIdUsuarioSeguridad(),
                vehiculoRespository.findByPlaca(request.getPlaca()).getId_vehiculo());
    }

    @Override
    public Registro registrarSalida(Integer idVehiculo) {
        Registro r = registroRepository.findRegistro(idVehiculo);
        r.setFecha_salida(LocalDateTime.now());
        registroRepository.save(r);
        return r;
    }

    @Override
    public List<DtoRegistro> obtenerRegistros() {
        List<DtoRegistro> lstRegistros = new ArrayList<>();
        for (Registro r : registroRepository.findRegistrosNoSalida()) {
            DtoRegistro dto = new DtoRegistro();
            dto.setId_registro(r.getId_registro());
            dto.setFecha_ingreso(r.getFecha_ingreso());
            dto.setFecha_salida(r.getFecha_salida());
            dto.setObservacion(r.getObservacion());
            dto.setIdUsuario(r.getUsuario().getId_usuario());
            dto.setIdUsuarioSeguridad(r.getUsuarioSeguridad().getId_usuario());
            dto.setPlacaVehiculo(r.getVehiculo().getPlaca());
            lstRegistros.add(dto);
        }
        return lstRegistros;
    }

    @Override
    public List<RegistroExportDTO> getAllRegistrosForExport() {
        List<Registro> registros = registroRepository.findAll();
        return mapRegistrosToDTOs(registros);
    }

    @Override
    public List<RegistroExportDTO> getRegistrosPorIntervaloFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Registro> registros = registroRepository.findByFechaIngresoBetween(fechaInicio, fechaFin);
        return mapRegistrosToDTOs(registros);
    }

    private List<RegistroExportDTO> mapRegistrosToDTOs(List<Registro> registros) {
        return registros.stream().map(registro -> RegistroExportDTO.builder()
                        .idRegistro(registro.getId_registro())
                        .fechaIngreso(registro.getFecha_ingreso())
                        .fechaSalida(registro.getFecha_salida())
                        .observacion(registro.getObservacion())
                        .estacionamientoNombre(registro.getEstacionamiento().getNombre())
                        .vehiculoPlaca(registro.getVehiculo().getPlaca())
                        .usuarioDni(registro.getUsuario().getDni())
                        .usuarioSeguridadUsername(registro.getUsuarioSeguridad().getUsername())
                        .build())
                .collect(Collectors.toList());
    }
}
