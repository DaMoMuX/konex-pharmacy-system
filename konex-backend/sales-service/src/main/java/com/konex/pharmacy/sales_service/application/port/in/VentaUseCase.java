package com.konex.pharmacy.sales_service.application.port.in;

import com.konex.pharmacy.sales_service.domain.model.Venta;
import java.time.LocalDateTime;
import java.util.List;

public interface VentaUseCase {
    Venta crearVenta(Venta venta);
    List<Venta> listarVentas();
    List<Venta> filtrarPorFecha(LocalDateTime desde, LocalDateTime hasta);
}