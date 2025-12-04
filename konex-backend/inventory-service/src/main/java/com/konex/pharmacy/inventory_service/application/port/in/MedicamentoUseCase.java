package com.konex.pharmacy.inventory_service.application.port.in;

import com.konex.pharmacy.inventory_service.domain.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicamentoUseCase {
    List<Medicamento> listar();
    Medicamento crear(Medicamento medicamento);
    Medicamento actualizar(Long id, Medicamento medicamento);
    void eliminar(Long id);
    Medicamento descontarStock(Long id, int cantidad);
    Page<Medicamento> listarPaginadoFiltrado(String s, Pageable pageable);
}
