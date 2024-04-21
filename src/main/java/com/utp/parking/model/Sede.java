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
@Table(name = "sedes")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sede;

    @Column(length = 50)
    private String nombre;

    @Column(length = 60)
    private String direccion;

    private Integer cantidad;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sede", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sede"})
    private List<Estacionamiento> estacionamientos;
}
