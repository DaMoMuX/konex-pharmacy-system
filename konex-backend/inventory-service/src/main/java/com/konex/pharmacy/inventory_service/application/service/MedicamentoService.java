package com.konex.pharmacy.inventory_service.application.service;

import com.konex.pharmacy.inventory_service.application.port.in.MedicamentoUseCase;
import com.konex.pharmacy.inventory_service.application.port.out.MedicamentoRepositoryPort;
import com.konex.pharmacy.inventory_service.domain.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService implements MedicamentoUseCase {

    private final MedicamentoRepositoryPort medicamentoRepositoryPort;

    public MedicamentoService(MedicamentoRepositoryPort medicamentoRepositoryPort) {
        this.medicamentoRepositoryPort = medicamentoRepositoryPort;
    }

    @Override
    public List<Medicamento> listar() {
        return medicamentoRepositoryPort.findAll();
    }

    @Override
    public Medicamento crear(Medicamento medicamento) {
        return medicamentoRepositoryPort.save(medicamento);
    }

    @Override
    public Medicamento actualizar(Long id, Medicamento medicamento) {
        Medicamento existente = medicamentoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        medicamento.setId(id);
        return medicamentoRepositoryPort.save(medicamento);
    }

    @Override
    public void eliminar(Long id) {
        medicamentoRepositoryPort.deleteById(id);
    }

    @Override
    public Medicamento descontarStock(Long id, int cantidad) {
        Medicamento medicamento = medicamentoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        if (medicamento.getCantidadStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        medicamento.setCantidadStock(medicamento.getCantidadStock() - cantidad);
        return medicamentoRepositoryPort.save(medicamento);
    }

    public Page<Medicamento> listarPaginadoFiltrado(String nombre, Pageable pageable) {
        return medicamentoRepositoryPort.findByNombreContainingIgnoreCase(nombre, pageable);
    }
}