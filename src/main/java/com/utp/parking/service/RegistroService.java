package com.utp.parking.service;

import com.utp.parking.interfaceService.IRegistroService;
import com.utp.parking.model.Registro;
import com.utp.parking.model.dto.request.DtoRegistroRequest;
import com.utp.parking.repository.RegistroRepository;
import com.utp.parking.repository.VehiculoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
