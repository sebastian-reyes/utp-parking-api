/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.parking.controller;

import com.utp.parking.interfaceService.IUsuarioService;
import com.utp.parking.interfaceService.IVehiculoService;
import com.utp.parking.model.Solicitud;
import com.utp.parking.model.dto.RegistroExportDTO;
import com.utp.parking.model.dto.SolicitudExportDTO;
import com.utp.parking.model.dto.VehiculoExportDTO;
import com.utp.parking.service.RegistroService;
import com.utp.parking.service.SolicitudService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author jvidal
 */
@RestController
@RequestMapping("/export")
public class ExcelExportController {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private IVehiculoService vehiculoService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/registros")
    public ResponseEntity<InputStreamResource> exportRegistrosToExcel() throws IOException {
        List<RegistroExportDTO> registros = registroService.getAllRegistrosForExport();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Registros");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID Registro");
        header.createCell(1).setCellValue("Fecha Ingreso");
        header.createCell(2).setCellValue("Hora Ingreso");
        header.createCell(3).setCellValue("Fecha Salida");
        header.createCell(4).setCellValue("Hora Salida");
        header.createCell(5).setCellValue("Observación");
        header.createCell(6).setCellValue("Estacionamiento");
        header.createCell(7).setCellValue("Vehículo");
        header.createCell(8).setCellValue("Usuario DNI");
        header.createCell(9).setCellValue("Usuario Seguridad");

        int rowIdx = 1;
        for (RegistroExportDTO registro : registros) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(registro.getIdRegistro());
            row.createCell(1).setCellValue(registro.getFechaIngresoFormatted());
            row.createCell(2).setCellValue(registro.getHoraIngresoFormatted());
            row.createCell(3).setCellValue(registro.getFechaSalida() != null ? registro.getFechaSalida().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : "");
            row.createCell(4).setCellValue(registro.getFechaSalida() != null ? registro.getFechaSalida().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : "");
            row.createCell(5).setCellValue(registro.getObservacion());
            row.createCell(6).setCellValue(registro.getEstacionamientoNombre());
            row.createCell(7).setCellValue(registro.getVehiculoPlaca());
            row.createCell(8).setCellValue(registro.getUsuarioDni());
            row.createCell(9).setCellValue(registro.getUsuarioSeguridadUsername());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=registros.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    @GetMapping("/registros-intervalo")
    public ResponseEntity<InputStreamResource> exportRegistrosPorIntervalo(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) throws IOException {
        LocalDateTime fechaInicioDateTime = fechaInicio.atStartOfDay();
        LocalDateTime fechaFinDateTime = fechaFin.atTime(LocalTime.MAX);

        List<RegistroExportDTO> registros = registroService.getRegistrosPorIntervaloFechas(fechaInicioDateTime, fechaFinDateTime);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Registros");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID Registro");
        header.createCell(1).setCellValue("Fecha Ingreso");
        header.createCell(2).setCellValue("Hora Ingreso");
        header.createCell(3).setCellValue("Fecha Salida");
        header.createCell(4).setCellValue("Hora Salida");
        header.createCell(5).setCellValue("Observación");
        header.createCell(6).setCellValue("Estacionamiento");
        header.createCell(7).setCellValue("Vehículo");
        header.createCell(8).setCellValue("Usuario DNI");
        header.createCell(9).setCellValue("Usuario Seguridad");

        int rowIdx = 1;
        for (RegistroExportDTO registro : registros) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(registro.getIdRegistro());
            row.createCell(1).setCellValue(registro.getFechaIngresoFormatted());
            row.createCell(2).setCellValue(registro.getHoraIngresoFormatted());
            row.createCell(3).setCellValue(registro.getFechaSalida() != null ? registro.getFechaSalida().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : "");
            row.createCell(4).setCellValue(registro.getFechaSalida() != null ? registro.getFechaSalida().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : "");
            row.createCell(5).setCellValue(registro.getObservacion());
            row.createCell(6).setCellValue(registro.getEstacionamientoNombre());
            row.createCell(7).setCellValue(registro.getVehiculoPlaca());
            row.createCell(8).setCellValue(registro.getUsuarioDni());
            row.createCell(9).setCellValue(registro.getUsuarioSeguridadUsername());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=registros_intervalo.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    @GetMapping("/vehiculos")
    public ResponseEntity<byte[]> exportVehiculosToExcel() throws IOException {
        List<VehiculoExportDTO> vehiculos = vehiculoService.getAllVehiculos();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Vehículos");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        String[] headers = {"ID Vehiculo", "Placa", "Aprobado", "Categoria", "Activo", "Username"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        for (VehiculoExportDTO vehiculo : vehiculos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(vehiculo.getIdVehiculo());
            row.createCell(1).setCellValue(vehiculo.getPlaca());
            row.createCell(2).setCellValue(vehiculo.isAprobado() ? "SI" : "NO");
            row.createCell(3).setCellValue(vehiculo.getCategoria());
            row.createCell(4).setCellValue(vehiculo.isActivo() ? "SI" : "NO");
            row.createCell(5).setCellValue(vehiculo.getUsername());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        byte[] excelContent = outputStream.toByteArray();
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headersResponse.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vehiculos.xlsx");

        return ResponseEntity.ok().headers(headersResponse).body(excelContent);
    }

    @GetMapping("/solicitudes")
    public ResponseEntity<InputStreamResource> exportSolicitudesToExcel() throws IOException {
        List<SolicitudExportDTO> solicitudes = solicitudService.getAllSolicitudesForExport();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Solicitudes");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID Solicitud");
        header.createCell(1).setCellValue("Estado");
        header.createCell(2).setCellValue("Fecha Respuesta");
        header.createCell(3).setCellValue("Hora Respuesta");
        header.createCell(4).setCellValue("Fecha Solicitud");
        header.createCell(5).setCellValue("Hora Solicitud");
        header.createCell(6).setCellValue("Codigo Alumno");
        header.createCell(7).setCellValue("Vehículo");

        int rowIdx = 1;
        for (SolicitudExportDTO solicitud : solicitudes) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(solicitud.getIdSolicitud());
            row.createCell(1).setCellValue(solicitud.getEstado());
            row.createCell(2).setCellValue(solicitud.getFechaRespuesta() != null ? solicitud.getFechaRespuesta().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : "");
            row.createCell(3).setCellValue(solicitud.getFechaRespuesta() != null ? solicitud.getFechaRespuesta().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : "");
            row.createCell(4).setCellValue(solicitud.getFechaSolicitudFormatted());
            row.createCell(5).setCellValue(solicitud.getHoraSolicitudFormatted());
            row.createCell(6).setCellValue(solicitud.getUsuarioUsername());
            row.createCell(7).setCellValue(solicitud.getVehiculoPlaca());

        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=solicitudes.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    @GetMapping("/solicitudes-intervalo")
    public ResponseEntity<InputStreamResource> exportSolicitudesPorIntervalo(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) throws IOException {
        LocalDateTime fechaInicioDateTime = fechaInicio.atStartOfDay();
        LocalDateTime fechaFinDateTime = fechaFin.atTime(LocalTime.MAX);

        List<SolicitudExportDTO> solicitudes = solicitudService.getSolicitudesPorIntervaloFechas(fechaInicioDateTime, fechaFinDateTime);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("solicitudes");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID Solicitud");
        header.createCell(1).setCellValue("Estado");
        header.createCell(2).setCellValue("Fecha Respuesta");
        header.createCell(3).setCellValue("Hora Respuesta");
        header.createCell(4).setCellValue("Fecha Solicitud");
        header.createCell(5).setCellValue("Hora Solicitud");
        header.createCell(6).setCellValue("Codigo Alumno");
        header.createCell(7).setCellValue("Vehículo");

        int rowIdx = 1;
        for (SolicitudExportDTO solicitud : solicitudes) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(solicitud.getIdSolicitud());
            row.createCell(1).setCellValue(solicitud.getEstado());
            row.createCell(2).setCellValue(solicitud.getFechaRespuesta() != null ? solicitud.getFechaRespuesta().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : "");
            row.createCell(3).setCellValue(solicitud.getFechaRespuesta() != null ? solicitud.getFechaRespuesta().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : "");
            row.createCell(4).setCellValue(solicitud.getFechaSolicitudFormatted());
            row.createCell(5).setCellValue(solicitud.getHoraSolicitudFormatted());
            row.createCell(6).setCellValue(solicitud.getUsuarioUsername());
            row.createCell(7).setCellValue(solicitud.getVehiculoPlaca());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=solicitudes_intervalo.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    @GetMapping("/solicitudes/{username}")
    public void exportSolicitudesByUsername(@PathVariable String username, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=solicitudes_" + username + ".xlsx");

        List<Solicitud> solicitudes = solicitudService.findBySolicitudByUsername(username);

        List<SolicitudExportDTO> solicitudesDTO = solicitudes.stream().map(solicitud -> SolicitudExportDTO.builder()
                        .idSolicitud(solicitud.getId_solicitud())
                        .estado(solicitud.getEstado())
                        .fechaSolicitud(solicitud.getFechaSolicitud())
                        .fechaRespuesta(solicitud.getFechaRespuesta())
                        .usuarioUsername(solicitud.getUsuario().getUsername())
                        .vehiculoPlaca(solicitud.getVehiculo().getPlaca())
                        .fechaSolicitudFormatted(solicitud.getFechaSolicitud().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .horaSolicitudFormatted(solicitud.getFechaSolicitud().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                        .build())
                .toList();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Solicitudes");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID Solicitud");
        header.createCell(1).setCellValue("Estado");
        header.createCell(2).setCellValue("Fecha Respuesta");
        header.createCell(3).setCellValue("Hora Respuesta");
        header.createCell(4).setCellValue("Fecha Solicitud");
        header.createCell(5).setCellValue("Hora Solicitud");
        header.createCell(6).setCellValue("Codigo Alumno");
        header.createCell(7).setCellValue("Vehículo");

        int rowIdx = 1;
        for (SolicitudExportDTO solicitud : solicitudesDTO) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(solicitud.getIdSolicitud());
            row.createCell(1).setCellValue(solicitud.getEstado());
            row.createCell(2).setCellValue(solicitud.getFechaRespuesta() != null ? solicitud.getFechaRespuesta().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : "");
            row.createCell(3).setCellValue(solicitud.getFechaRespuesta() != null ? solicitud.getFechaRespuesta().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : "");
            row.createCell(4).setCellValue(solicitud.getFechaSolicitudFormatted());
            row.createCell(5).setCellValue(solicitud.getHoraSolicitudFormatted());
            row.createCell(6).setCellValue(solicitud.getUsuarioUsername());
            row.createCell(7).setCellValue(solicitud.getVehiculoPlaca());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @GetMapping("/seguridad")
    public ResponseEntity<Resource> exportUsuariosSeguridad() {
        try {
            Resource resource = usuarioService.exportUsuariosSeguridadToExcel();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=usuarios_seguridad.xlsx")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
