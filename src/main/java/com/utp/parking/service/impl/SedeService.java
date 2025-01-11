package com.utp.parking.service;

import com.utp.parking.interfaceService.ISedeService;
import com.utp.parking.model.Sede;
import com.utp.parking.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeService implements ISedeService {

    @Autowired
    private SedeRepository repository;

    @Override
    public List<Sede> listarSedes() {
        return repository.findAll();
    }

    @Override
    public Sede buscarSede(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void actualizarCantidadEspacios(Sede sede) {
        repository.save(sede);
    }
}
