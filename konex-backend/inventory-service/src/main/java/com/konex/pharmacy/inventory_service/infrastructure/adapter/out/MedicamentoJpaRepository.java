package com.konex.pharmacy.inventory_service.infrastructure.adapter.out;

import com.konex.pharmacy.inventory_service.application.port.out.MedicamentoRepositoryPort;
import com.konex.pharmacy.inventory_service.domain.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

interface SpringDataMedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Page<Medicamento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}

// Adapter OUT
@Component
public class MedicamentoJpaRepository implements MedicamentoRepositoryPort {

    private final SpringDataMedicamentoRepository repository;

    public MedicamentoJpaRepository(SpringDataMedicamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Medicamento> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Medicamento save(Medicamento medicamento) {
        return repository.save(medicamento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Medicamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Medicamento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable) {
        return repository.findByNombreContainingIgnoreCase(nombre, pageable);
    }
}