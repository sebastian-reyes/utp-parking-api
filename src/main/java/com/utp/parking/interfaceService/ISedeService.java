package com.utp.parking.interfaceService;

import com.utp.parking.model.Sede;
import com.utp.parking.model.dto.DtoSede;

import java.util.List;

public interface ISedeService {
    public List<Sede> listarSedes();
    public Sede buscarSede(int id);
    public void actualizarCantidadEspacios(Sede sede);
}
