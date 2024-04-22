package com.utp.parking.repository;

import com.utp.parking.model.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstacionamientoRepository extends JpaRepository<Estacionamiento, Integer> {
    @Query("SELECT e FROM Estacionamiento e WHERE e.sede.id_sede = :idSede AND e.piso = :piso AND e.disponible = true")
    List<Estacionamiento> findByIdSedeAndPiso(int idSede, int piso);
}
