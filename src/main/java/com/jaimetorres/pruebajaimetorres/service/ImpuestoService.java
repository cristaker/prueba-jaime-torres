package com.jaimetorres.pruebajaimetorres.service;

import com.jaimetorres.pruebajaimetorres.mapper.ImpuestoMapper;
import com.jaimetorres.pruebajaimetorres.model.dto.ImpuestoDto;
import com.jaimetorres.pruebajaimetorres.model.dto.ImpuestoFechaDTO;
import com.jaimetorres.pruebajaimetorres.model.dto.ResumenHorarioDTO;
import com.jaimetorres.pruebajaimetorres.model.entities.Impuesto;
import com.jaimetorres.pruebajaimetorres.repository.ImpuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImpuestoService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private final ImpuestoRepository repository;
    private final ImpuestoMapper mapper;
    private final ExcelExporterService excelExporterService;
    private final CsvLoaderService csvLoaderService;

    public void cargar(MultipartFile file) throws IOException {
        List<Impuesto> data = csvLoaderService.parseTxt(file);
        repository.saveAll(data);
    }

    public List<ImpuestoFechaDTO> consultarDatosPorFecha(String fechaStr) {
        LocalDate date = LocalDate.parse(fechaStr, FORMATTER);
        List<Impuesto> impuestos = repository.findByFechaMovimiento(date);
        return impuestos.stream()
                .map(mapper::toImpuestoFechaDTO)
                .toList();
    }

    public ResumenHorarioDTO resumenPorHorario(String tipo) {
        List<Impuesto> lista = repository.findByTipoHorario(tipo);
        long total = lista.size();
        long suma = lista.stream().mapToLong(Impuesto::getValor).sum();
        return new ResumenHorarioDTO(total, suma);
    }

    public Optional<ImpuestoDto> obtenerPorSticker(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    public InputStreamResource generarExcelPorFecha(String fecha) throws IOException {
        LocalDate date = LocalDate.parse(fecha, FORMATTER);
        List<Impuesto> data = repository.findByFechaMovimiento(date);
        ByteArrayInputStream in = excelExporterService.export(data);
        return new InputStreamResource(in);
    }
}