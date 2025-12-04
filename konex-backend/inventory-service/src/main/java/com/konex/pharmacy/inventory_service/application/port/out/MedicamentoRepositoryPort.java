package com.konex.pharmacy.inventory_service.application.port.out;

import com.konex.pharmacy.inventory_service.domain.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MedicamentoRepositoryPort {
    List<Medicamento> findAll();
    Optional<Medicamento> findById(Long id);
    Medicamento save(Medicamento medicamento);
    void deleteById(Long id);
    Page<Medicamento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}