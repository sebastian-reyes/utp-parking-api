package com.utp.parking.repository;

import com.utp.parking.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRespository extends JpaRepository<Vehiculo, Integer> {
    public Vehiculo findByPlaca(String placa);
}
