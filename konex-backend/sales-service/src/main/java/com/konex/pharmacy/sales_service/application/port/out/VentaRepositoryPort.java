package com.konex.pharmacy.sales_service.application.port.out;

import com.konex.pharmacy.sales_service.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepositoryPort {
    Venta save(Venta venta);
    List<Venta> findAll();
    Page<Venta> findAll(Pageable pageable);
    Page<Venta> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta, Pageable pageable);
}