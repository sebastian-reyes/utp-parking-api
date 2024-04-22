package com.utp.parking.service;

import com.utp.parking.interfaceService.IEstacionamientoService;
import com.utp.parking.model.Estacionamiento;
import com.utp.parking.repository.EstacionamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionamientoService implements IEstacionamientoService {

    @Autowired
    private EstacionamientoRepository repository;

    @Override
    public List<Estacionamiento> listarEstacionamientos(int idSede, int piso) {
        return repository.findByIdSedeAndPiso(idSede, piso);
    }
}
