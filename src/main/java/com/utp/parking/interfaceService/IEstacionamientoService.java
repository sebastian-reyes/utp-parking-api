package com.utp.parking.interfaceService;

import com.utp.parking.model.Estacionamiento;

import java.util.List;

public interface IEstacionamientoService {
    public List<Estacionamiento> listarEstacionamientos(int idSede, int piso);
    public Estacionamiento buscarEstacionamiento(int idEstacionamiento);
}
