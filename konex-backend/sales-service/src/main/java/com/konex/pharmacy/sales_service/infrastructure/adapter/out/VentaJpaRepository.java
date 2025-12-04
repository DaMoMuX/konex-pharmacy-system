package com.konex.pharmacy.sales_service.infrastructure.adapter.out;

import com.konex.pharmacy.sales_service.application.port.out.VentaRepositoryPort;
import com.konex.pharmacy.sales_service.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

interface SpringDataVentaRepository extends JpaRepository<Venta, Long> {
    Page<Venta> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta, Pageable pageable);
}

// Adapter OUT
@Component
public class VentaJpaRepository implements VentaRepositoryPort {

    private final SpringDataVentaRepository repository;

    public VentaJpaRepository(SpringDataVentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Venta save(Venta venta) {
        return repository.save(venta);
    }

    @Override
    public List<Venta> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Venta> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Venta> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta, Pageable pageable) {
        return repository.findByFechaHoraBetween(desde, hasta, pageable);
    }
}