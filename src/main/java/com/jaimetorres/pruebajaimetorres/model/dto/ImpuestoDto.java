package com.jaimetorres.pruebajaimetorres.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ImpuestoDto {

    private Long sticker;
    private LocalDate fechaMovimiento;
    private LocalDate fechaRecaudo;
    private String tipoHorario;
    private Long nroId;
    private Long nroForm;
    private Long valor;
}
