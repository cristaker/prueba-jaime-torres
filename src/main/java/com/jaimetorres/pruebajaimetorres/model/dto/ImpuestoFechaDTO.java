package com.jaimetorres.pruebajaimetorres.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpuestoFechaDTO {

    private Long sticker;
    private LocalDate fechaMovimiento;
    private Long nroId;
    private Long nroForm;
    private Long valor;
}
