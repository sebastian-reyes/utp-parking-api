/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.parking.service;

import com.utp.parking.interfaceService.IUsuarioService;
import com.utp.parking.model.Role;
import com.utp.parking.model.Usuario;
import com.utp.parking.model.dto.UsuarioExportDTO;
import com.utp.parking.repository.UsuarioRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jvidal
 */
@Service
public class UsuarioService implements IUsuarioService {
  
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioExportDTO> exportAllUsuarios() {
        return usuarioRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Resource exportUsuariosSeguridadToExcel() throws IOException {
        List<Usuario> usuariosSeguridad = usuarioRepository.findByRole(Role.SEGURIDAD);
        List<UsuarioExportDTO> dtos = usuariosSeguridad.stream().map(this::convertToDTO).collect(Collectors.toList());

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Usuarios Seguridad");

        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID Usuario", "Username", "Nombres", "Apellidos", "Correo Institucional", "DNI", "Role"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (UsuarioExportDTO dto : dtos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dto.getIdUsuario());
            row.createCell(1).setCellValue(dto.getUsername());
            row.createCell(2).setCellValue(dto.getNombres());
            row.createCell(3).setCellValue(dto.getApellidos());
            row.createCell(4).setCellValue(dto.getCorreoInstitucional());
            row.createCell(5).setCellValue(dto.getDni());
            row.createCell(6).setCellValue(dto.getRole());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new ByteArrayResource(outputStream.toByteArray());
    }

    private UsuarioExportDTO convertToDTO(Usuario usuario) {
        UsuarioExportDTO dto = new UsuarioExportDTO();
        dto.setIdUsuario(usuario.getId_usuario());
        dto.setUsername(usuario.getUsername());
        dto.setNombres(usuario.getNombres());
        dto.setApellidos(usuario.getApellidos());
        dto.setCorreoInstitucional(usuario.getCorreoInstitucional());
        dto.setDni(usuario.getDni());
        dto.setMatriculado(usuario.getMatriculado());
        dto.setRole(usuario.getRole().name());
        return dto;
    }
    
    @Override
    public List<UsuarioExportDTO> findUsuariosByRoleSeguridad() {
        List<Usuario> usuariosSeguridad = usuarioRepository.findByRole(Role.SEGURIDAD);
        return usuariosSeguridad.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
