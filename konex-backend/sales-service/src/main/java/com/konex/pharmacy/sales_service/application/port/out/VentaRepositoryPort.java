package com.konex.pharmacy.sales_service.application.port.out;

import com.konex.pharmacy.sales_service.domain.model.Venta;
import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepositoryPort {
    Venta save(Venta venta);
    List<Venta> findAll();
    List<Venta> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta);
}