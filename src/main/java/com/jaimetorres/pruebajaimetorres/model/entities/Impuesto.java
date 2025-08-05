package com.jaimetorres.pruebajaimetorres.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "impuestos")
public class Impuesto {
    @Id
    private Long sticker;
    private LocalDate fechaMovimiento;
    private LocalDate fechaRecaudo;
    private String tipoHorario;
    private Long nroId;
    private Long nroForm;
    private Long valor;
}

