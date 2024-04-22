package com.utp.interfaceService;

import com.utp.parking.model.Sede;

import java.util.List;

public interface ISedeService {
    public List<Sede> listarSedes();
    public Sede buscarSede(int id);
}
