package com.konex.pharmacy.sales_service.application.port.in;

import com.konex.pharmacy.sales_service.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaUseCase {
    Venta crearVenta(Venta venta);
    List<Venta> listarVentas();
    List<Venta> filtrarPorFecha(LocalDateTime desde, LocalDateTime hasta);

    Page<Venta> filtrarPorFechaPaginado(LocalDateTime desde, LocalDateTime hasta, Pageable pageable);

    Page<Venta> listarVentasPaginado(Pageable pageable);
}