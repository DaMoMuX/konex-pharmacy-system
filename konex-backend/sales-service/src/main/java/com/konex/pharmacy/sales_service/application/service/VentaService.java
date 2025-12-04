package com.konex.pharmacy.sales_service.application.service;

import com.konex.pharmacy.sales_service.application.port.in.VentaUseCase;
import com.konex.pharmacy.sales_service.application.port.out.VentaRepositoryPort;
import com.konex.pharmacy.sales_service.application.port.out.InventoryClientPort;
import com.konex.pharmacy.sales_service.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService implements VentaUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;
    private final InventoryClientPort inventoryClientPort;

    public VentaService(VentaRepositoryPort ventaRepositoryPort, InventoryClientPort inventoryClientPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
        this.inventoryClientPort = inventoryClientPort;
    }

    @Override
    public Venta crearVenta(Venta venta) {
        var medicamento = inventoryClientPort.descontarStockYObtenerDatos(venta.getMedicamentoId(), venta.getCantidad());

        venta.setFechaHora(LocalDateTime.now());
        venta.setValorUnitario(medicamento.getValorUnitario());
        venta.setValorTotal(venta.getValorUnitario() * venta.getCantidad());
        return ventaRepositoryPort.save(venta);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepositoryPort.findAll();
    }

    @Override
    public List<Venta> filtrarPorFecha(LocalDateTime desde, LocalDateTime hasta) {
        Page<Venta> page = ventaRepositoryPort.findByFechaHoraBetween(desde, hasta, Pageable.unpaged());
        return page.getContent();
    }

    @Override
    public Page<Venta> filtrarPorFechaPaginado(LocalDateTime desde, LocalDateTime hasta, Pageable pageable) {
        return ventaRepositoryPort.findByFechaHoraBetween(desde, hasta, pageable);
    }

    @Override
    public Page<Venta> listarVentasPaginado(Pageable pageable) {
        return ventaRepositoryPort.findAll(pageable);
    }
}