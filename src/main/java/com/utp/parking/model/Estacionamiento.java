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
@Table(name = "estacionamientos")
public class Estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_estacionamiento;

    private Integer piso;

    private Integer numero;

    @Column(length = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sede")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sedes", "id_sede"})
    private Sede sede;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estacionamiento", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "estacionamiento"})
    private List<Registro> registros;

}
