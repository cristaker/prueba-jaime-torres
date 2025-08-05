package com.jaimetorres.pruebajaimetorres.service;

import com.jaimetorres.pruebajaimetorres.model.entities.Impuesto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExporterService {

    public ByteArrayInputStream export(List<Impuesto> data) throws IOException {
        try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Impuestos");
            Row header = sheet.createRow(0);
            String[] columns = {"Información del Sticker", "Fecha de Movimiento", "Fecha de Recaudo", "Tipo de Horario", "Número de identificación", "Número de Formulario", "Valor"};
            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }
            int rowIdx = 1;
            for (Impuesto i : data) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(i.getSticker());
                row.createCell(1).setCellValue(i.getFechaMovimiento().toString());
                row.createCell(2).setCellValue(i.getFechaRecaudo().toString());
                row.createCell(3).setCellValue(i.getTipoHorario());
                row.createCell(4).setCellValue(i.getNroId());
                row.createCell(5).setCellValue(i.getNroForm());
                row.createCell(6).setCellValue(i.getValor());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}