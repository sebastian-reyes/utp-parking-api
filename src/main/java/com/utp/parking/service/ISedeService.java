package com.utp.parking.interfaceService;

import com.utp.parking.model.Sede;

import java.util.List;

public interface ISedeService {
    List<Sede> listarSedes();
    Sede buscarSede(int id);
    void actualizarCantidadEspacios(Sede sede);
}
