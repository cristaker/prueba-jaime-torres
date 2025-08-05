package com.jaimetorres.pruebajaimetorres.repository;

import com.jaimetorres.pruebajaimetorres.model.entities.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {
    List<Impuesto> findByFechaMovimiento(LocalDate fecha);
    List<Impuesto> findByTipoHorario(String tipoHorario);
}
