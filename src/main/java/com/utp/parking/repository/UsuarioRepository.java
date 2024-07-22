package com.utp.parking.repository;

import com.utp.parking.model.Role;
import com.utp.parking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByRole(Role role);
}
