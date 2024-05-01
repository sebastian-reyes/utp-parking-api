package com.utp.parking.interfaceService;

import com.utp.parking.model.Estacionamiento;

import java.util.List;

public interface IEstacionamientoService {
    List<Estacionamiento> listarEstacionamientos(int idSede, int piso);
    Estacionamiento buscarEstacionamiento(int idEstacionamiento);
}
