package com.utp.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_vehiculo;

    @Column(unique = true, length = 15)
    private String placa;

    private boolean aprovado;

    @Column(length = 20)
    private String categoria;

    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "vehiculos", "id_usuario"})
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehiculo", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "vehiculo"})
    private List<Registro> registros;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehiculo", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "vehiculo"})
    private List<Solicitud> solicitudes;
}
