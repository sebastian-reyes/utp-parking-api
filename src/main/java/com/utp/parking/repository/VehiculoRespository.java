package com.utp.parking.repository;

import com.utp.parking.model.Vehiculo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehiculoRespository extends JpaRepository<Vehiculo, Integer> {
    public Vehiculo findByPlaca(String placa);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO vehiculos(id_vehiculo,placa,activo,aprobado,categoria,id_usuario) " +
            "VALUES (NULL, :placa, false, false, :categoria, :id_usuario)",
            nativeQuery = true)
    void registrarVehiculo(@Param("categoria") String categoria,
                           @Param("placa") String placa,
                           @Param("id_usuario") int id_usuario);
}
