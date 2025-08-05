package com.jaimetorres.pruebajaimetorres.controller;

import com.jaimetorres.pruebajaimetorres.model.dto.ErrorResponse;
import com.jaimetorres.pruebajaimetorres.model.dto.ImpuestoFechaDTO;
import com.jaimetorres.pruebajaimetorres.model.dto.ResumenHorarioDTO;
import com.jaimetorres.pruebajaimetorres.service.ImpuestoService;
import com.jaimetorres.pruebajaimetorres.utils.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/impuestos")
@RequiredArgsConstructor
public class ImpuestoController {

    private final ImpuestoService service;

    @PostMapping("/cargar")
    public ResponseEntity<?> cargar(@RequestParam("file") MultipartFile file) throws IOException {
        service.cargar(file);
        return ResponseEntity.ok("Archivo cargado correctamente");
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<ImpuestoFechaDTO>> consultarPorFecha(@RequestParam("fecha") String fecha) {
        List<ImpuestoFechaDTO> impuestoFecha = service.consultarDatosPorFecha(fecha);
        return ResponseEntity.ok(impuestoFecha);
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResumenHorarioDTO> obtenerResumenPorHorario(@RequestParam("tipo") String tipo) {
        ResumenHorarioDTO resumen = service.resumenPorHorario(tipo);
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/sticker/{id}")
    public ResponseEntity<?> buscarPorSticker(@PathVariable Long id) {
        return service.obtenerPorSticker(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse(false, 404, ErrorMessage.STICKER_NOT_FOUND.getMessage())));
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportarExcel(@RequestParam("fecha") String fecha) throws IOException {
        InputStreamResource recurso = service.generarExcelPorFecha(fecha);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=impuestos.xls");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(recurso);
    }
}