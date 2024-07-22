/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.parking.controller;

import com.utp.parking.interfaceService.IUsuarioService;
import com.utp.parking.model.dto.UsuarioExportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jvidal
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UsuarioController {
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping("/seguridad")
    public ResponseEntity<?> listarUsuariosSeguridad() {
        Map<String, Object> response = new HashMap<>();
        List<UsuarioExportDTO> lstUsuariosSeguridad = usuarioService.findUsuariosByRoleSeguridad();
        response.put("usuarios", lstUsuariosSeguridad);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
