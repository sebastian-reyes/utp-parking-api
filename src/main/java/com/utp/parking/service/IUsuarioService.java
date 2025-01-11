/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.parking.interfaceService;

import com.utp.parking.model.dto.UsuarioExportDTO;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author jvidal
 */
public interface IUsuarioService {
    List<UsuarioExportDTO> exportAllUsuarios();
    Resource exportUsuariosSeguridadToExcel() throws IOException;
    List<UsuarioExportDTO> findUsuariosByRoleSeguridad();
}
