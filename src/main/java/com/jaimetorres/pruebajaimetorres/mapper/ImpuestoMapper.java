package com.jaimetorres.pruebajaimetorres.mapper;

import com.jaimetorres.pruebajaimetorres.model.dto.ImpuestoDto;
import com.jaimetorres.pruebajaimetorres.model.dto.ImpuestoFechaDTO;
import com.jaimetorres.pruebajaimetorres.model.entities.Impuesto;
import org.springframework.stereotype.Component;

@Component
public class ImpuestoMapper {

    public ImpuestoDto toDto(Impuesto impuesto) {
        ImpuestoDto dto = new ImpuestoDto();
        dto.setSticker(impuesto.getSticker());
        dto.setFechaMovimiento(impuesto.getFechaMovimiento());
        dto.setFechaRecaudo(impuesto.getFechaRecaudo());
        dto.setTipoHorario(impuesto.getTipoHorario());
        dto.setNroId(impuesto.getNroId());
        dto.setNroForm(impuesto.getNroForm());
        dto.setValor(impuesto.getValor());
        return dto;
    }

    public ImpuestoFechaDTO toImpuestoFechaDTO(Impuesto dto) {
        ImpuestoFechaDTO impuestoFecha = new ImpuestoFechaDTO();
        impuestoFecha.setSticker(dto.getSticker());
        impuestoFecha.setFechaMovimiento(dto.getFechaMovimiento());
        impuestoFecha.setNroId(dto.getNroId());
        impuestoFecha.setNroForm(dto.getNroForm());
        impuestoFecha.setValor(dto.getValor());
        return impuestoFecha;
    }
}

