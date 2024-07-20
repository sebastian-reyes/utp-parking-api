package com.utp.parking.repository;

import com.utp.parking.model.Solicitud;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    @Query("SELECT s from Solicitud s where s.usuario.id_usuario = :usuarioId")
    List<Solicitud> findByIdUsuario(Integer usuarioId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO solicitudes(id_solicitud,estado,fecha_solicitud,id_usuario,id_vehiculo,fecha_respuesta) " +
            "VALUES (NULL,'Por verificar', CURRENT_TIMESTAMP, :id_usuario, :id_vehiculo, NULL)",
            nativeQuery = true)
    void registrarSolicitud(@Param("id_usuario") int id_usuario, @Param("id_vehiculo") int id_vehiculo);

    @Query("SELECT s FROM Solicitud s WHERE s.fechaSolicitud BETWEEN :start AND :end")
    List<Solicitud> findByFechaSolicitudBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    List<Solicitud> findByUsuarioUsername(String username);
}
