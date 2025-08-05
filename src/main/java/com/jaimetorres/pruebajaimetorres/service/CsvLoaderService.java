package com.jaimetorres.pruebajaimetorres.service;

import com.jaimetorres.pruebajaimetorres.model.entities.Impuesto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvLoaderService {

    public List<Impuesto> parseTxt(MultipartFile file) throws IOException {
        List<Impuesto> impuestos = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyddMM");

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            Impuesto impuesto = new Impuesto();
            impuesto.setSticker(Long.parseLong(fields[0]));
            impuesto.setFechaMovimiento(LocalDate.parse(fields[1], formatter));
            impuesto.setFechaRecaudo(LocalDate.parse(fields[2], formatter));
            impuesto.setTipoHorario(fields[3]);
            impuesto.setNroId(Long.parseLong(fields[4]));
            impuesto.setNroForm(Long.parseLong(fields[5]));
            impuesto.setValor(Long.parseLong(fields[6]));
            impuestos.add(impuesto);
        }
        return impuestos;
    }
}

